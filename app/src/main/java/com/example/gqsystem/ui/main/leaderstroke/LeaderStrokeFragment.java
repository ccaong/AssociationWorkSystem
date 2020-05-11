package com.example.gqsystem.ui.main.leaderstroke;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.main.leaderstroke.LeaderActivityEditFragment.PARAM_LEADER_ACTIVITY;

/**
 * @author devel
 * 领导动态，行程
 */
public class LeaderStrokeFragment extends BaseFragment<FragmentListBinding, LeaderStrokeViewModel> {

    private CommonAdapter<LeaderActivityListBean.RecordsBean> commonAdapter;
    private int pos;
    private LeaderActivityListBean.RecordsBean bean;

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
        mViewModel = new ViewModelProvider(this).get(LeaderStrokeViewModel.class);
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

    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refresh -> mViewModel.refreshData(false));
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        commonAdapter = new CommonAdapter<LeaderActivityListBean.RecordsBean>(R.layout.leader_recycle_item_stroke, BR.leaderActivity) {
            @Override
            public void addListener(View root, LeaderActivityListBean.RecordsBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.tv_active_file_content).setOnClickListener(v -> {
                    bean = itemData;
                    pos = position;
                    requestPermission(itemData.getActivityFile());
                });

                //点击编辑
                root.findViewById(R.id.item).setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(PARAM_LEADER_ACTIVITY, itemData);
                    NavHostFragment.findNavController(LeaderStrokeFragment.this).navigate(R.id.nav_leader_activity_edit, bundle);
                });

                //长按删除
                root.findViewById(R.id.item).setOnLongClickListener(v -> {
                    mViewModel.delete(itemData.getId());
                    return false;
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
        mViewModel.getLeaderActivity().observe(this, activityListBean -> {
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
                bean.setDownloadProgress(progress);
                commonAdapter.notifyItemChanged(pos);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            //新增会议
            NavHostFragment.findNavController(LeaderStrokeFragment.this).navigate(R.id.nav_leader_activity_edit);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}