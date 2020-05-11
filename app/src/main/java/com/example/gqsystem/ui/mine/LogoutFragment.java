package com.example.gqsystem.ui.mine;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.FragmentLogoutBinding;

import androidx.lifecycle.ViewModelProviders;

/**
 * @author : devel
 * @date : 2020/3/27 10:34
 * @desc : 退出
 */
public class LogoutFragment extends BaseFragment<FragmentLogoutBinding, MineViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_logout;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(getActivity()).get(MineViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        mViewModel.logout();
    }
}
