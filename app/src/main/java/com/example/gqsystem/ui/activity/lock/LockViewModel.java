package com.example.gqsystem.ui.activity.lock;

import com.example.gqsystem.R;
import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.UserDataBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.util.CommonUtils;
import com.orhanobut.hawk.Hawk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/3/2 10:47
 * @desc :
 */
public class LockViewModel extends BaseViewModel {

    public MutableLiveData<UserDataBean> userBean;
    public MutableLiveData<Boolean> usePwd = new MutableLiveData<>();
    public MutableLiveData<String> userName;
    public MutableLiveData<String> userPwd;

    public MutableLiveData<String> loginTitle;

    public MutableLiveData<Integer> loginStatus;

    public LockViewModel() {
        userBean = new MutableLiveData<>();
        UserDataBean userDataBean = Hawk.get(Constants.HawkCode.LOGIN_DATA);
        if (userDataBean != null) {
            userBean.postValue(userDataBean);
        }

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
        String defaultPwd = "123456";
        UserDataBean userDataBean = Hawk.get(Constants.HawkCode.LOGIN_DATA);
        if (CommonUtils.isStringEmpty(userDataBean.getUserInfo().getUserpwd())) {
            defaultPwd = userDataBean.getUserInfo().getUserpwd();
        }
        if (defaultPwd.equals(userPwd.getValue())) {
            //成功
            cancelPwd();
            loginStatus.postValue(0);
        } else {
            // 失败
            userPwd.postValue("");
            loginTitle.postValue(getResources().getString(R.string.input_pwd_error));
        }
    }
}
