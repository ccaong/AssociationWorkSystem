package com.example.gqsystem.ui.mine;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.MineFragmentContentBinding;

import androidx.lifecycle.ViewModelProviders;

/**
 * @author : devel
 * @date : 2020/3/19 13:51
 * @desc :
 */
public class MineFragment extends BaseFragment<MineFragmentContentBinding, MineViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.mine_fragment_content;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(getActivity()).get(MineViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }

    private void getDataChange() {

    }
}
