package com.example.gqsystem.ui.activity.forgetpwd;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.UserEmailBean;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;

/**
 * @author : devel
 * @date : 2020/3/23 17:22
 * @desc :
 */
public class ForgetPwdViewMode extends BaseViewModel {

    /**
     * 是否发送验证码
     */
    public MutableLiveData<Boolean> isSendVerificationCode;

    /**
     * 是否校验成功
     */
    private MutableLiveData<Boolean> verifySuccess;

    /**
     * 密码重置是否成功
     */
    private MutableLiveData<Boolean> resetSuccess;

    /**
     * 用户名
     */
    public MutableLiveData<String> userName;

    /**
     * 验证码
     */
    public MutableLiveData<String> verificationCode;
    private MutableLiveData<String> mTime;
    /**
     * 密码
     */
    public MutableLiveData<String> userPwd;
    public MutableLiveData<String> userRePwd;

    /**
     * 提示信息
     */
    public MutableLiveData<String> emailToast;

    private UserEmailBean userEmailBean;


    public ForgetPwdViewMode() {
        isSendVerificationCode = new MutableLiveData<>(false);
        verifySuccess = new MutableLiveData<>(false);
        resetSuccess = new MutableLiveData<>(false);
        userName = new MutableLiveData<>();
        emailToast = new MutableLiveData<>();
        verificationCode = new MutableLiveData<>();
        mTime = new MutableLiveData<>();

        userPwd = new MutableLiveData<>();
        userRePwd = new MutableLiveData<>();
    }

    public LiveData<String> getTime() {
        return mTime;
    }

    /**
     * 验证码校验是否通过
     *
     * @return 验证码校验情况
     */
    public LiveData<Boolean> getVerifySuccess() {
        return verifySuccess;
    }

    /**
     * 是否重置成功
     *
     * @return
     */
    public LiveData<Boolean> getResetSuccess() {
        return resetSuccess;
    }


    /**
     * 获取用户信息
     */
    public void getUserEmail() {
        if (CommonUtils.isStringEmpty(userName.getValue())) {
            ToastUtils.showToast("请输入用户名");
            return;
        }

        HttpRequest.getInstance()
                .getEmailByUserName(userName.getValue())
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<UserEmailBean>() {
                    @Override
                    public void success(UserEmailBean bean) {
                        userEmailBean = bean;
                        sendVerificationCode();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast(e.getMessage());
                    }
                });
    }


    /**
     * 发送验证码
     */
    private void sendVerificationCode() {

        if (isSendVerificationCode.getValue() != null && isSendVerificationCode.getValue()) {
            return;
        }

        Map map = new HashMap();
        map.put("email", userEmailBean.getEmail());
        map.put("smsmode", "2");
        HttpRequest.getInstance()
                .sendVerificationCode(map)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<Object>() {
                    @Override
                    public void success(Object bean) {
                        emailToast.postValue("验证码发送至" + userEmailBean.getEmail() + "邮箱");
                        startTime();
                        isSendVerificationCode.postValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        isSendVerificationCode.postValue(false);
                        ToastUtils.showToast(e.getMessage());
                    }
                });
    }

    /**
     * 倒计时
     */
    private void startTime() {
        List<String> list = new ArrayList<>();
        for (int i = 60; i >= 0; i--) {
            list.add(i + "");
        }
        Observable<String> observable = Observable.fromIterable(list);
        Observable<Long> time = Observable.interval(1, TimeUnit.SECONDS);
        Observable.zip(observable, time, (s, aLong) -> s).subscribe(str -> {
            if ("0".equals(str)) {
                isSendVerificationCode.postValue(false);
            }
            mTime.postValue("已发送 (" + str + "s)");
        });
    }

    /**
     * 校验验证码
     */
    public void verify() {
        if (CommonUtils.isStringEmpty(verificationCode.getValue())) {
            ToastUtils.showToast("请输入邮箱中的验证码");
            return;
        }
        if (userEmailBean == null) {
            ToastUtils.showToast("请先发送验证码！");
            return;
        }
        Map map = new HashMap();
        map.put("email", userEmailBean.getEmail());
        map.put("smscode", verificationCode.getValue());
        HttpRequest.getInstance()
                .emailVerification(map)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<Object>() {
                    @Override
                    public void success(Object bean) {
                        verifySuccess.postValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        verifySuccess.postValue(false);
                        ToastUtils.showToast(e.getMessage());
                    }
                });
    }

    /**
     * 重置密码
     */
    public void changePwd() {
        if (CommonUtils.isStringEmpty(userPwd.getValue())) {
            ToastUtils.showToast("密码不能为空");
            return;
        }

        if (!userPwd.getValue().equals(userRePwd.getValue())) {
            ToastUtils.showToast("两次输入的密码不一致,请重新输入");
            return;
        }

        HttpRequest.getInstance()
                .changePwd(userName.getValue(), userEmailBean.getEmail(), verificationCode.getValue(), userPwd.getValue())
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<Object>() {
                    @Override
                    public void success(Object bean) {
                        ToastUtils.showToast("密码重置成功,请登录");
                        resetSuccess.postValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        resetSuccess.postValue(false);
                        ToastUtils.showToast(e.getMessage());
                    }
                });
    }
}
