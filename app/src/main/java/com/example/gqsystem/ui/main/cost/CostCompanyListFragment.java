package com.example.gqsystem.ui.main.cost;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.FragmentListBinding;

import androidx.lifecycle.ViewModelProvider;

/**
 * @author devel
 */
public class CostCompanyListFragment extends BaseFragment<FragmentListBinding, CostCompanyListViewModel> {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(CostCompanyListViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {

    }

}
