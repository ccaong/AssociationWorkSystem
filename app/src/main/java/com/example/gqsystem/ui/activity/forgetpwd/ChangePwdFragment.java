package com.example.gqsystem.ui.activity.forgetpwd;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.ForgetpwdFragmentChangePwdBinding;

import androidx.lifecycle.ViewModelProvider;

/**
 * @author : devel
 * @date : 2020/3/24 9:31
 * @desc :
 */
public class ChangePwdFragment extends BaseFragment<ForgetpwdFragmentChangePwdBinding, ForgetPwdViewMode> {

    @Override
    protected int getLayoutResId() {
        return R.layout.forgetpwd_fragment_change_pwd;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(getActivity()).get(ForgetPwdViewMode.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
