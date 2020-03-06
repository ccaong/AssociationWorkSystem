package com.example.gqsystem.ui.main.leaderstroke;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.FragmentListBinding;

import androidx.lifecycle.ViewModelProvider;

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

    }


}