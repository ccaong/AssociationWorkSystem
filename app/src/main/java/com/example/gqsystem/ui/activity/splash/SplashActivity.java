package com.example.gqsystem.ui.activity.splash;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivitySplashBinding;
import com.example.gqsystem.ui.activity.lock.LockActivity;
import com.example.gqsystem.ui.activity.login.LoginActivity;
import com.example.gqsystem.util.ActivitySkipUtil;

import static com.example.gqsystem.ui.activity.lock.LockActivity.LOCK_TYPE;

/**
 * @author : devel
 * @date : 2020/2/24 9:22
 * @desc :
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    protected boolean isNoActionBar() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
    }

    @Override
    protected void bindViewModel() {
    }

    @Override
    protected void init() {
        mViewModel.getTime().observe(this, s -> {
            if (s.equals("Login")) {
                ActivitySkipUtil.skipActivity(SplashActivity.this, LoginActivity.class);
                finish();
            }
            if (s.equals("Main")) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(LOCK_TYPE, LockActivity.lockType.VERIFICATION);
                ActivitySkipUtil.skipActivity(SplashActivity.this, LockActivity.class, bundle);
                finish();
            }
        });
    }
}
