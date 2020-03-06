package com.example.gqsystem.ui.activity.lock;

import com.example.gqsystem.R;
import com.example.gqsystem.base.viewmodel.BaseViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/3/2 10:47
 * @desc :
 */
public class LockViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> usePwd = new MutableLiveData<>();
    public MutableLiveData<String> userName;
    public MutableLiveData<String> userPwd;

    public MutableLiveData<String> loginTitle;

    public MutableLiveData<Integer> loginStatus;

    public LockViewModel() {
        loginTitle = new MutableLiveData<>();
        loginTitle.postValue(getResources().getString(R.string.input_pwd));

        loginStatus = new MutableLiveData<>();

        userName = new MutableLiveData<>();
        userPwd = new MutableLiveData<>();
        usePwd.postValue(false);
    }


    public LiveData<Integer> getLoginStatus() {
        return loginStatus;
    }

    /**
     * 使用密码验证
     */
    public void usePwdVerification() {
        usePwd.postValue(true);
    }


    /**
     * 取消密码验证
     */
    public void cancelPwd() {
        usePwd.postValue(false);
    }

    /**
     * 使用密码登录
     */
    public void loginWithPwd() {

        // TODO: 2020/3/2 失败
        userPwd.postValue("");
        loginTitle.postValue(getResources().getString(R.string.input_pwd_error));

        // TODO: 2020/3/2 成功
        cancelPwd();
        loginStatus.postValue(0);
    }

}
