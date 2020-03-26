package com.example.gqsystem.ui.metting.list;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.FragmentListBinding;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author devel
 */
public class MeetingFragment extends BaseFragment<FragmentListBinding, MeetingViewModel> {


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
        mViewModel = new ViewModelProvider(this).get(MeetingViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        setHasOptionsMenu(true);

        mViewModel.loadData();
        initRefreshLayout();
    }


    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refresh -> mViewModel.refreshData(false));
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
