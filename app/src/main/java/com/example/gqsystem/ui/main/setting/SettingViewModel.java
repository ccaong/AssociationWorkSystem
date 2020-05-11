package com.example.gqsystem.ui.main.setting;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.config.Constants;
import com.orhanobut.hawk.Hawk;

import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class SettingViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> mSwitchReader;
    public MutableLiveData<Boolean> mSwitchReadOnly;
    public MutableLiveData<Boolean> mSwitchDownload;

    public SettingViewModel() {
        mSwitchReader = new MutableLiveData<>();
        mSwitchReadOnly = new MutableLiveData<>();
        mSwitchDownload = new MutableLiveData<>();
        init();
    }

    public void init() {
        //是否优先使用WPS
        Boolean useWps = Hawk.get(Constants.SettingCode.OPEN_FILE_WITH_WPS, false);
        mSwitchReader.postValue(useWps);

        //是否使用只读模式
        Boolean readOnly = Hawk.get(Constants.SettingCode.READ_ONLY_TYPE, true);
        mSwitchReadOnly.postValue(readOnly);

        //是否允许使用数据网络下载文件
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