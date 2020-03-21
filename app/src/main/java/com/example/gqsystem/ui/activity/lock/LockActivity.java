package com.example.gqsystem.ui.activity.lock;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.gqsystem.MainActivity;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivityLockBinding;
import com.example.gqsystem.manager.configmanager.BiometricPromptManager;
import com.example.gqsystem.util.ActivitySkipUtil;
import com.example.gqsystem.util.ToastUtils;


/**
 * @author : devel
 * @date : 2020/3/2 10:46
 * @desc :验证使用用户，解锁手机
 */
public class LockActivity extends BaseActivity<ActivityLockBinding, LockViewModel> {

    public static final String LOCK_TYPE = "lock_type";

   public enum lockType {
        /**
         * 解锁
         */
        UNLOCK,

        /**
         * 登陆验证
         */
        VERIFICATION
    }

    private BiometricPromptManager mManager;

    private boolean supportFinger = true;

    private lockType type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        type = (lockType) bundle.getSerializable(LOCK_TYPE);
    }

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

        mViewModel.getLoginStatus().observe(this, integer -> {
            if (integer == 0) {
                verificationSuccess();
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
                    Log.e("FINGER_STATUS", "onUsePassword");
                    mViewModel.usePwdVerification();
                }

                @Override
                public void onSucceeded() {
                    Log.e("FINGER_STATUS", "onSucceeded");
                    verificationSuccess();
                }

                @Override
                public void onFailed() {
                    //验证失败
                    Log.e("FINGER_STATUS", "onFailed");
                }

                @Override
                public void onError(int code, String reason) {
                    Log.e("FINGER_STATUS", "onError" + code + reason);

                    if (code == 7) {
                        ToastUtils.showToast(reason);
                        //验证失败次数过多，调用密码
                        mViewModel.usePwdVerification();
                    }
                }

                @Override
                public void onCancel() {
                    Log.e("FINGER_STATUS", "onCancel");
                    //点击取消
                }
            });
        }
    }

    /**
     * 验证成功
     */
    private void verificationSuccess() {
        if (type == lockType.UNLOCK) {
            //解锁
            Toast.makeText(LockActivity.this, getResources().getString(R.string.finger_success), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            //登陆
            Toast.makeText(LockActivity.this, getResources().getString(R.string.finger_success), Toast.LENGTH_SHORT).show();
            ActivitySkipUtil.skipActivity(LockActivity.this, MainActivity.class);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        // 屏蔽返回键
        Toast.makeText(this, getResources().getString(R.string.finger_title), Toast.LENGTH_SHORT).show();
    }
}

