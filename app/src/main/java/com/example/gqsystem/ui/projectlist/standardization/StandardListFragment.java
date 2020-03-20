package com.example.gqsystem.ui.projectlist.standardization;

import android.os.Bundle;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author devel
 */
public class StandardListFragment extends BaseFragment<FragmentListBinding, StandardListViewModel> {

    private static final String PARAM1 = "";

    private int type;

    private CommonAdapter adapter;

    public static StandardListFragment newInstance(int type) {
        StandardListFragment articleListFragment = new StandardListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM1, type);
        articleListFragment.setArguments(bundle);
        return articleListFragment;
    }

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        type = args.getInt(PARAM1);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(StandardListViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        mViewModel.setType(type);

        initRefreshLayout();
        initRecyclerView();
    }


    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setPrimaryColorsId(android.R.color.white, R.color.colorPrimary);
        mDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        mDataBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mViewModel.refreshData(true);
                refreshlayout.finishRefresh(2000);
            }
        });
        mDataBinding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                mViewModel.refreshData(false);
                refreshlayout.finishLoadMore(2000);
            }
        });
    }


    private void initRecyclerView() {
//        adapter = new CommonAdapter(R.layout.standard_item_todo, );
        mDataBinding.recyclerView.setAdapter(adapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 数据刷新
     */
    private void dataChange() {

        mViewModel.loadData();
    }
}
