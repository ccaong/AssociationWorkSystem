package com.example.gqsystem.ui.activity.forgetpwd;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivityForgetPwdBinding;

import androidx.lifecycle.ViewModelProvider;

/**
 * @author : devel
 * @date : 2020/3/23 17:21
 * @desc : 忘记密码，重置密码
 */
public class ForgetPwdActivity extends BaseActivity<ActivityForgetPwdBinding, ForgetPwdViewMode> {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(ForgetPwdViewMode.class);
    }

    @Override
    protected void bindViewModel() {
    }

    @Override
    protected void init() {
        mViewModel.getResetSuccess().observe(this, aBoolean -> {
            if (aBoolean) {
                finish();
            }
        });
    }
}
