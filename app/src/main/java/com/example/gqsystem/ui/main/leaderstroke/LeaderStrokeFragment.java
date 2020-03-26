package com.example.gqsystem.ui.main.leaderstroke;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

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
            bean.setDownloadProgress(100 * progress);
            commonAdapter.notifyItemChanged(pos);
        });
    }

    /**
     * 检查权限,跳转到读取文档的界面
     *
     * @param filePath
     */
    private void requestPermission(String filePath) {
        mViewModel.isFileExists(filePath);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (null != mHandler) {
//            mHandler.removeMessages(1);
//            mHandler = null;
//        }
    }
}