package com.example.gqsystem.ui.activity.login;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.UserDataBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.ToastUtils;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

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


    /**
     * 用户点击登录
     */
    public void login() {
        if (CommonUtils.isStringEmpty(userName.getValue()) ||
                CommonUtils.isStringEmpty(userPwd.getValue())) {
            loginStatus.postValue("1");
            return;
        }
        login(userName.getValue(), userPwd.getValue());
    }

    /**
     * 请求
     */
    private void login(String name, String pwd) {
        Map map = new HashMap(2);
        map.put("captcha", "");
        map.put("checkKey", "");
        map.put("username", name);
        map.put("password", pwd);

        HttpRequest.getInstance()
                .login(map)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<UserDataBean>() {
                    @Override
                    public void success(UserDataBean bean) {
                        bean.getUserInfo().setUserpwd(pwd);
                        Hawk.put(Constants.HawkCode.LOGIN_DATA, bean);
                        //登录成功
                        loginStatus.postValue("0");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //登录失败
                        ToastUtils.showToast(e.getMessage());
                    }
                });
    }
}
