package com.example.gqsystem.ui.activity.login;

import androidx.lifecycle.ViewModelProviders;

import com.example.gqsystem.MainActivity;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivityLoginBinding;
import com.example.gqsystem.util.ActivitySkipUtil;

/**
 * @author : devel
 * @date : 2020/2/18 16:42
 * @desc :
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initData();
    }

    private void initData() {
        mViewModel.getLoginStatus().observe(this, s -> {
            if ("0".equals(s)) {//登录成功
                ActivitySkipUtil.skipActivity(LoginActivity.this, MainActivity.class);
                finish();
            }
        });
    }
}
