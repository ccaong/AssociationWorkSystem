package com.example.gqsystem.ui.update;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.util.AppVersionUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/3/27 11:10
 * @desc : 更新
 */
public class UpdateViewModel extends BaseViewModel {

    private MutableLiveData<List<String>> updatePlanList;
    public MutableLiveData<String> version;

    public UpdateViewModel() {
        updatePlanList = new MutableLiveData<>();
        version = new MutableLiveData<>();
        initUpdatePlanList();
        getVersion();
    }

    public LiveData<List<String>> getUpdateList() {
        return updatePlanList;
    }

    private void initUpdatePlanList() {
        List<String> mList = new ArrayList<>();
        mList.add("第一个版本");
        updatePlanList.postValue(mList);
    }

    private void getVersion() {
        version.postValue("V"+AppVersionUtil.getAPPLocalVersion());
    }
}
