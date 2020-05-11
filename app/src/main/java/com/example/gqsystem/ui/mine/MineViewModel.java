package com.example.gqsystem.ui.mine;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.UserDataBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.orhanobut.hawk.Hawk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/3/19 13:53
 * @desc :
 */
public class MineViewModel extends BaseViewModel {

    public MutableLiveData<UserDataBean> mUserData;

    private MutableLiveData<Integer> logoutStatus;

    public MineViewModel() {
        logoutStatus = new MutableLiveData<>();
        mUserData = new MutableLiveData<>();
    }

    /**
     * 设置用户信息
     *
     * @param userData
     */
    public void setUserData(UserDataBean userData) {
        mUserData.postValue(userData);
    }

    /**
     * 获取退出状态
     *
     * @return 退出状态
     */
    public LiveData<Integer> getLogoutStatus() {
        return logoutStatus;
    }

    /**
     * 退出
     */
    public void logout() {
        HttpRequest.getInstance()
                .logout()
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<Object>() {
                    @Override
                    public void success(Object bean) {
                        Hawk.delete(Constants.HawkCode.LOGIN_DATA);
                        logoutStatus.postValue(-1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        logoutStatus.postValue(-1);
                        Hawk.delete(Constants.HawkCode.LOGIN_DATA);
                        mUserData.postValue(null);
                    }
                });
    }
}
