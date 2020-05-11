package com.example.gqsystem.ui.datashare;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.MainActivity;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.DataShareListBean;
import com.example.gqsystem.common.ImageDialog;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.Objects;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author devel
 */
public class DataShareFragment extends BaseFragment<FragmentListBinding, DataShareViewModel> {

    private CommonAdapter<DataShareListBean.RecordsBean> commonAdapter;
    private int pos;
    private DataShareListBean.RecordsBean bean;

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(DataShareViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        setHasOptionsMenu(true);
        mViewModel.loadData();
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        //通过MenuItem得到SearchView
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.setSearchText(newText);
                return false;
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setPrimaryColorsId(android.R.color.white, R.color.colorPrimary);
        mDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(getContext())));
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refresh -> mViewModel.refreshData(false));
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        commonAdapter = new CommonAdapter<DataShareListBean.RecordsBean>(R.layout.data_share_item_layout, BR.dataShare) {
            @Override
            public void addListener(View root, DataShareListBean.RecordsBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.item).setOnClickListener(v -> {
                    bean = itemData;
                    pos = position;
                    requestPermission(itemData.getDataFile());
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(commonAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 更新数据
     */
    @Override
    protected void initDataChange() {
        mViewModel.getDataShareList().observe(this, activityListBean -> {
            //刷新完成，更新数据
            if (activityListBean.getCurrent() >= activityListBean.getPages()) {
                mDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
            mDataBinding.refreshLayout.finishRefresh();
            mDataBinding.refreshLayout.finishLoadMore();
            if (commonAdapter != null) {
                commonAdapter.onItemDatasChanged(activityListBean.getRecords());
            }
        });

        mViewModel.getDownLoadProgress().observe(this, progress -> {
            if (bean != null && commonAdapter != null) {
                bean.setProgress(progress);
                commonAdapter.notifyItemChanged(pos);
            }
        });
    }
}
