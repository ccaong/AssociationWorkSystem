package com.example.gqsystem.ui.activity.forgetpwd;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.ForgetpwdFragmentSendVerificationBinding;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

/**
 * @author : devel
 * @date : 2020/3/24 9:39
 * @desc : 发送验证码
 */
public class SendVerificationFragment extends BaseFragment<ForgetpwdFragmentSendVerificationBinding, ForgetPwdViewMode> {
    @Override
    protected int getLayoutResId() {
        return R.layout.forgetpwd_fragment_send_verification;
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

        mViewModel.getVerifySuccess().observe(this, aBoolean -> {
            if (aBoolean) {
                NavHostFragment.findNavController(SendVerificationFragment.this).navigate(R.id.changePwdFragment);
            }
        });

        mViewModel.getTime().observe(this, s -> {
            if ("已发送 (0s)".equals(s)) {
                mDataBinding.btnSendVerification.setText("发送验证码");
            } else {
                mDataBinding.btnSendVerification.setText(s);
            }
        });
    }
}
