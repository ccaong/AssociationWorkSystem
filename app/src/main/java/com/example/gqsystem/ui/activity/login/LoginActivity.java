package com.example.gqsystem.ui.activity.login;

import com.example.gqsystem.MainActivity;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivityLoginBinding;
import com.example.gqsystem.ui.activity.forgetpwd.ForgetPwdActivity;
import com.example.gqsystem.util.ActivitySkipUtil;
import com.example.gqsystem.util.ToastUtils;

import androidx.lifecycle.ViewModelProviders;

/**
 * @author : devel
 * @date : 2020/2/18 16:42
 * @desc :登录
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
        mDataBinding.setClickListener(new EventListener());
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


    /**
     * 点击事件
     */
    public class EventListener {

        /**
         * 忘记密码
         */
        public void btnForgetPwd() {
            ActivitySkipUtil.skipActivity(LoginActivity.this, ForgetPwdActivity.class);
        }
    }
}
