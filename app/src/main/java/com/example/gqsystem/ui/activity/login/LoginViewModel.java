package com.example.gqsystem.ui.activity.login;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.bean.response.LoginData;
import com.example.gqsystem.util.CommonUtils;
import com.orhanobut.hawk.Hawk;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/2/24 10:35
 * @desc :
 */
public class LoginViewModel extends BaseViewModel {


    private MutableLiveData<String> loginStatus;
    public MutableLiveData<String> userName;
    public MutableLiveData<String> userPwd;

    public LoginViewModel() {
        loginStatus = new MutableLiveData<>();
        userName = new MutableLiveData<>();
        userPwd = new MutableLiveData<>();
    }


    public MutableLiveData<String> getLoginStatus() {
        return loginStatus;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        // TODO: 2020/2/24
        // TODO: 2020/2/24 从缓存中读取用户信息
        LoginData loginData = Hawk.get(Constants.HawkCode.LOGIN_DATA);
        if (loginData != null) {
            // TODO: 2020/2/24 缓存中有用户信息， 自动登录验证
            login("", "", true);
        } else {
            // TODO: 2020/2/24 没有缓存的用户信息，显示登录界面
        }

    }


    /**
     * 用户点击登录
     */
    public void login() {
        if (CommonUtils.isStringEmpty(userName.getValue()) ||
                CommonUtils.isStringEmpty(userPwd.getValue())) {
            loginStatus.postValue("1");
            return;
        }
        login(userName.getValue(), userPwd.getValue(), false);
    }

    /**
     * 请求
     */
    private void login(String name, String pwdb, boolean auto) {
        // TODO: 2020/2/24 请求接口，登录


        // TODO: 2020/2/24 登录成功
        if (auto) {
            // TODO: 2020/2/24  自动登录成功，验证指纹
            verifyFingerprint();

        } else {
            // TODO: 2020/2/24 手动登录，直接跳转
            loginStatus.postValue("0");
        }
        // TODO: 2020/2/24 登录失败
//        loginStatus.postValue("1");
    }

    /**
     * 验证指纹
     */
    private void verifyFingerprint() {

        // TODO: 2020/2/24 验证成功
        loginStatus.postValue("0");

        // TODO: 2020/2/24 验证失败
        loginStatus.postValue("2");

    }
}
