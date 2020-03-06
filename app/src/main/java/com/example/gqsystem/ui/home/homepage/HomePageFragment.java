package com.example.gqsystem.ui.home.homepage;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.HomePageBean;
import com.example.gqsystem.databinding.HomePageFragmentBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.util.GlideUtil;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author devel
 */
public class HomePageFragment extends BaseFragment<HomePageFragmentBinding, HomePageViewModel> {

    private CommonAdapter<HomePageBean> commonAdapter;
    /**
     * 是否为线性布局
     */
    private boolean linearLayoutManager = true;

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_page_fragment;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
    }

    @Override
    protected void initDataChange() {

        mViewModel.getHomeList().observe(this, new Observer<List<HomePageBean>>() {
            @Override
            public void onChanged(List<HomePageBean> homePageBeans) {
                if (commonAdapter != null) {
                    commonAdapter.onItemDatasChanged(homePageBeans);
                }
            }
        });

        mViewModel.getBannerList().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> list) {
                initBanner(list);
            }
        });
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
        mDataBinding.setHomeFragment(this);

    }

    @Override
    protected void init() {
        mViewModel.initData();
        initRefreshLayout();
        initRecycleView();
    }

    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setPrimaryColorsId(android.R.color.white, R.color.colorPrimary);
        mDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));

        mDataBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                refreshlayout.finishRefresh(1000);
            }
        });
    }

    private void initBanner(List<Integer> mList) {
        //设置banner样式
        mDataBinding.banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器
        mDataBinding.banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GlideUtil.loadImage(imageView, path);
            }
        });
        //设置图片集合
        mDataBinding.banner.setImages(mList);
        //banner设置方法全部调用完毕时最后调用
        mDataBinding.banner.start();
    }

    private void initRecycleView() {
        commonAdapter = new CommonAdapter<HomePageBean>(R.layout.home_item_project_type, BR.homeData) {

            @Override
            public void addListener(View root, HomePageBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        };
        mDataBinding.recyclerViewHome.setAdapter(commonAdapter);
        mDataBinding.recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void changeLayoutManager() {

        if (linearLayoutManager) {
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            mDataBinding.recyclerViewHome.setLayoutManager(layoutManager);
            linearLayoutManager = false;
            mDataBinding.fabTop.setImageResource(R.mipmap.ic_view_list);

        } else {
            mDataBinding.recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
            linearLayoutManager = true;
            mDataBinding.fabTop.setImageResource(R.mipmap.ic_view_grid);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDataBinding.banner.stopAutoPlay();
    }
}
