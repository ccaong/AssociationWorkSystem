package com.example.gqsystem.ui.project.cost;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.ProjectCostAccountBean;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/4/2 10:00
 * @desc :成本核算
 */
public class ProjectCostAccountViewModel extends BaseViewModel {

    private MutableLiveData<ProjectCostAccountBean> projectCostAccountBean;
    private MutableLiveData<List<FileBean>> mCostFile;

    public ProjectCostAccountViewModel() {
        projectCostAccountBean = new MutableLiveData<>();
        mCostFile = new MutableLiveData<>();
    }

    public LiveData<ProjectCostAccountBean> getCostBean() {
        return projectCostAccountBean;
    }


    /**
     * 附件
     *
     * @return
     */
    public LiveData<List<FileBean>> getCostFile() {
        return mCostFile;
    }


    public void loadData(String id) {
        loadState.postValue(LoadState.LOADING);
        HttpRequest.getInstance()
                .queryProCostAccounting(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<ProjectCostAccountBean>>() {
                    @Override
                    public void success(List<ProjectCostAccountBean> bean) {
                        if (bean == null || bean.size() <= 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        projectCostAccountBean.postValue(bean.get(0));
                        if (CommonUtils.isStringEmpty(bean.get(0).getAccessory())) {
                            return;
                        }
                        String[] fileData = bean.get(0).getAccessory().split(",");
                        ArrayList<FileBean> arrayList = new ArrayList<>(fileData.length);
                        for (String name : fileData) {
                            arrayList.add(new FileBean(name, "", 0));
                        }
                        mCostFile.postValue(arrayList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }
}
