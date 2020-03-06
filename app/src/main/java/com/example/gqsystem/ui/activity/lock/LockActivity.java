package com.example.gqsystem.ui.activity.lock;

import android.widget.Toast;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivityLockBinding;
import com.example.gqsystem.manager.configmanager.BiometricPromptManager;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


/**
 * @author : devel
 * @date : 2020/3/2 10:46
 * @desc :长时间不操作手机，锁定手机
 */
public class LockActivity extends BaseActivity<ActivityLockBinding, LockViewModel> {

    private BiometricPromptManager mManager;

    private boolean supportFinger = true;

    @Override
    protected boolean isNoActionBar() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_lock;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(LockViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
        mDataBinding.setActivity(this);
    }

    @Override
    protected void init() {
        try {
            mManager = BiometricPromptManager.from(this);
        } catch (Exception e) {
            supportFinger = false;
        }

        mViewModel.getLoginStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 0) {
                    Toast.makeText(LockActivity.this, getResources().getString(R.string.finger_success), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    /**
     * 指纹验证
     */
    public void fingerVerification() {

        if (!supportFinger) {
            //没有录入指纹信息，使用密码登录
            mViewModel.usePwdVerification();
            return;
        }

        if (mManager.isBiometricPromptEnable()) {
            mManager.authenticate(new BiometricPromptManager.OnBiometricIdentifyCallback() {
                @Override
                public void onUsePassword() {
                    mViewModel.usePwdVerification();
                }

                @Override
                public void onSucceeded() {
                    Toast.makeText(LockActivity.this, getResources().getString(R.string.finger_success), Toast.LENGTH_SHORT).show();
                    //验证成功
                    finish();
                }

                @Override
                public void onFailed() {
                    Toast.makeText(LockActivity.this, "onFailed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(int code, String reason) {
                    //验证错误，调用密码
                    mViewModel.usePwdVerification();
                }

                @Override
                public void onCancel() {
                    //点击取消
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        // 屏蔽返回键
        Toast.makeText(this,  getResources().getString(R.string.finger_title), Toast.LENGTH_SHORT).show();
    }
}

