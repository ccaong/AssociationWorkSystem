package com.example.gqsystem.ui.home.homepage;

import android.content.Context;
import android.os.Bundle;
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
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.gqsystem.ui.project.list.ProjectListFragment.PARAM_PRO_STATUS;
import static com.example.gqsystem.ui.project.list.ProjectListFragment.PARAM_PRO_TYPE;

/**
 * @author devel
 */
public class HomePageFragment extends BaseFragment<HomePageFragmentBinding, HomePageViewModel> {

    private CommonAdapter<HomePageBean> commonAdapter;
    /**
     * 是否为线性布局
     */
    private boolean isLinearLayoutManager = false;

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

        mViewModel.getHomeList().observe(this, homePageBeans -> {
            if (commonAdapter != null) {
                commonAdapter.onItemDatasChanged(homePageBeans);
            }
        });

        mViewModel.getBannerList().observe(this, this::initBanner);
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

        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> refresh.finishRefresh(1000));
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
                root.findViewById(R.id.card_view).setOnClickListener(v -> click(itemData));
            }
        };
        mDataBinding.recyclerViewHome.setAdapter(commonAdapter);
        mDataBinding.recyclerViewHome.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    /**
     * 点击不同的项目类型
     *
     * @param homePageBean
     */
    private void click(HomePageBean homePageBean) {

        switch (homePageBean.getName()) {
            case "资料共享":
                NavHostFragment.findNavController(HomePageFragment.this).navigate(R.id.data_share);
                break;
            case "待开发":
                Bundle bundleToDo = new Bundle();
                bundleToDo.putString(PARAM_PRO_STATUS, "1");
                NavHostFragment.findNavController(HomePageFragment.this).navigate(R.id.project_list, bundleToDo);
                break;
            case "进行中":
                Bundle bundleDoing = new Bundle();
                bundleDoing.putString(PARAM_PRO_STATUS, "2,3,4,5,6,7,8,9");
                NavHostFragment.findNavController(HomePageFragment.this).navigate(R.id.project_list, bundleDoing);
                break;
            case "标准化":
            case "双重预防机制":
            case "评估评价":
            case "安全管理体系提升":
            case "安全生产信息化":
            case "专项整治":
            case "政府采购":
                Bundle bundle = new Bundle();
                bundle.putString(PARAM_PRO_TYPE, homePageBean.getName());
                NavHostFragment.findNavController(HomePageFragment.this).navigate(R.id.project_viewpager, bundle);
                break;
            default:
                break;
        }
    }


    /**
     * 改变外观
     */
    public void changeLayoutManager() {
        if (isLinearLayoutManager) {
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            mDataBinding.recyclerViewHome.setLayoutManager(layoutManager);
            isLinearLayoutManager = false;
            mDataBinding.fabTop.setImageResource(R.mipmap.ic_view_list);

        } else {
            mDataBinding.recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
            isLinearLayoutManager = true;
            mDataBinding.fabTop.setImageResource(R.mipmap.ic_view_grid);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDataBinding.banner.stopAutoPlay();
    }
}
