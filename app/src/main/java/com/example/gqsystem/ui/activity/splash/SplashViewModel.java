package com.example.gqsystem.ui.activity.splash;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.UserDataBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;

/**
 * @author : devel
 * @date : 2020/2/24 9:33
 * @desc :
 */
public class SplashViewModel extends BaseViewModel {

    private MutableLiveData<String> mTime;


    public SplashViewModel() {
        mTime = new MutableLiveData<>();
    }

    public LiveData<String> getTime() {
        return mTime;
    }


    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        login();
    }

    /**
     * 自动登录
     */
    private void login() {
        UserDataBean userDataBean = Hawk.get(Constants.HawkCode.LOGIN_DATA);
        if (userDataBean != null) {
            //缓存中有用户信息， 自动登录验证
            login(userDataBean.getUserInfo().getUsername(), userDataBean.getUserInfo().getUserpwd());
        } else {
            //没有缓存的用户信息，显示登录界面
            startTime(false);
        }
    }

    /**
     * 请求
     */
    private void login(String name, String pwd) {

        Map map = new HashMap(2);
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
                        startTime(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        startTime(false);
                    }
                });
    }

    /**
     * 闪屏页倒计时
     *
     * @param loginSuccess
     */
    private void startTime(Boolean loginSuccess) {

        List<String> list = new ArrayList<>();
        for (int i = 2; i >= 0; i--) {
            list.add(i + "");
        }

        Observable<String> observable = Observable.fromIterable(list);
        Observable<Long> time = Observable.interval(1, TimeUnit.SECONDS);

        Observable.zip(observable, time, (s, aLong) -> s).subscribe(str -> {
            if ("0".equals(str)) {
                if (loginSuccess) {
                    mTime.postValue("Main");
                } else {
                    mTime.postValue("Login");
                }
            }
        });
    }
}
