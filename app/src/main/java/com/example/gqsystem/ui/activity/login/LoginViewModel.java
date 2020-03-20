package com.example.gqsystem.ui.activity.login;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.UserDataBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.util.CommonUtils;
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

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        // 从缓存中读取用户信息
        UserDataBean userDataBean = Hawk.get(Constants.HawkCode.LOGIN_DATA);
        if (userDataBean != null) {
            // 缓存中有用户信息， 自动登录验证
            login(userDataBean.getUserInfo().getUsername(), userDataBean.getUserInfo().getUserpwd(), true);
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
    private void login(String name, String pwd, boolean auto) {
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
                        if (auto) {
                            //自动登录成功，验证指纹
                            verifyFingerprint();
                        } else {
                            //手动登录，直接跳转
                            loginStatus.postValue("0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //登录失败
                    }
                });
    }

    /**
     * 验证指纹
     */
    private void verifyFingerprint() {

        // TODO: 2020/2/24 验证成功
        loginStatus.postValue("0");

        // TODO: 2020/2/24 验证失败
//        loginStatus.postValue("2");

    }
}
