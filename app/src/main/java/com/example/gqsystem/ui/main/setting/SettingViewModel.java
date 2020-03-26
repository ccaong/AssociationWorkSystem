package com.example.gqsystem.ui.main.setting;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.config.Constants;
import com.orhanobut.hawk.Hawk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author devel
 */
public class SettingViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> mSwitchReader;
    public MutableLiveData<Boolean> mSwitchDownload;

    public SettingViewModel() {
        mSwitchReader = new MutableLiveData<>();
        mSwitchDownload = new MutableLiveData<>();
        init();
    }

    public void init() {
        Boolean useWps = Hawk.get(Constants.SettingCode.OPEN_FILE_WITH_WPS, false);
        mSwitchReader.postValue(useWps);

        Boolean allowData = Hawk.get(Constants.SettingCode.ALLOW_USE_DATA_DOWNLOAD, false);
        mSwitchDownload.postValue(allowData);
    }


    /**
     * 修改
     */
    public void changeReaderSwitcher() {

        if (mSwitchReader.getValue()) {
            mSwitchReader.postValue(false);
            Hawk.put(Constants.SettingCode.OPEN_FILE_WITH_WPS, false);
        } else {
            mSwitchReader.postValue(true);
            Hawk.put(Constants.SettingCode.OPEN_FILE_WITH_WPS, true);
        }

    }
}