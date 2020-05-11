package com.example.gqsystem.ui.metting.list;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.bean.response.MeetingListBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.util.ToastUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author devel
 */
public class MeetingFragment extends BaseFragment<FragmentListBinding, MeetingViewModel> {

    CommonAdapter<MeetingListBean.RecordsBean> commonAdapter;
    private int pos;
    private MeetingListBean.RecordsBean bean;

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
        mViewModel = new ViewModelProvider(getActivity()).get(MeetingViewModel.class);
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

    private void initRecyclerView() {

        commonAdapter = new CommonAdapter<MeetingListBean.RecordsBean>(R.layout.meeting_item_list, BR.meeting) {
            @Override
            public void addListener(View root, MeetingListBean.RecordsBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.card_view).setOnClickListener(v -> {
                    mViewModel.setMeetingBean(itemData);
                    NavHostFragment.findNavController(MeetingFragment.this).navigate(R.id.meeting_content);
                });

                root.findViewById(R.id.tv_meeting_notice).setOnClickListener(v -> {
                    bean = itemData;
                    pos = position;
                    String fileName = itemData.getMeetingNotice();
                    int i = itemData.getMeetingNotice().indexOf(',');
                    if (i != -1) {
                        fileName = itemData.getMeetingNotice().substring(0, i);
                    }
                    String finalFileName = fileName;
                    requestPermission(finalFileName);
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(commonAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initDataChange() {
        mViewModel.getMeetingList().observe(this, meetingListBean -> {
            if (meetingListBean.getCurrent() >= meetingListBean.getPages()) {
                mDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
            mDataBinding.refreshLayout.finishRefresh();
            mDataBinding.refreshLayout.finishLoadMore();
            if (commonAdapter != null) {
                commonAdapter.onItemDatasChanged(meetingListBean.getRecords());
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
            // TODO: 2020/3/5 新增会议 
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
