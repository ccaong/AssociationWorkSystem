package com.example.gqsystem.ui.activity.login;

import com.example.gqsystem.MainActivity;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivityLoginBinding;
import com.example.gqsystem.ui.activity.lock.LockActivity;
import com.example.gqsystem.util.ActivitySkipUtil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
        mViewModel.getLoginStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch (s) {
                    case "0":
                        //登录成功
                        ActivitySkipUtil.skipActivity(LoginActivity.this, MainActivity.class);
                        break;
                    case "1":
                        //登录失败
                        ActivitySkipUtil.skipActivity(LoginActivity.this, LockActivity.class);

//                        Toast.makeText(LoginActivity.this, "登录失败，请检查用户名或密码是否正确", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
