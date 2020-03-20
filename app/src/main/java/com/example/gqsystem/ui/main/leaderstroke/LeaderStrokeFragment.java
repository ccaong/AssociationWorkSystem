package com.example.gqsystem.ui.main.leaderstroke;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author devel
 * 领导动态，行程
 */
public class LeaderStrokeFragment extends BaseFragment<FragmentListBinding, LeaderStrokeViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(LeaderStrokeViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        mViewModel.loadData();
        initRefreshLayout();
        initRecyclerView();
    }

    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refreshlayout -> mViewModel.refreshData(false));
    }

    private void initRecyclerView() {

        CommonAdapter<LeaderActivityListBean.RecordsBean> commonAdapter = new CommonAdapter<>(R.layout.leader_recycle_item_stroke, BR.leaderActivity);

        mDataBinding.recyclerView.setAdapter(commonAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getLeaderActivity().observe(this, activityListBean -> {
            if (activityListBean.getCurrent() >= activityListBean.getPages()) {
                mDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
            mDataBinding.refreshLayout.finishRefresh();
            mDataBinding.refreshLayout.finishLoadMore();
            commonAdapter.onItemDatasChanged(activityListBean.getRecords());
        });
    }

}