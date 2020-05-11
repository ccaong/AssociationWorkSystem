package com.example.gqsystem.ui.project.contract;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.ProjectContractInfoBean;
import com.example.gqsystem.bean.response.ProjectContractPaymentBean;
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
 * @date : 2020/4/1 14:07
 * @desc : 合同信息
 */
public class ProjectContractViewModel extends BaseViewModel {

    private String id;

    private MutableLiveData<ProjectContractInfoBean> mContractInfo;
    private MutableLiveData<List<ProjectContractPaymentBean>> mContractPaymentList;

    private MutableLiveData<List<FileBean>> fileList;

    public ProjectContractViewModel() {
        mContractInfo = new MutableLiveData<>();
        mContractPaymentList = new MutableLiveData<>();
        fileList = new MutableLiveData<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public LiveData<ProjectContractInfoBean> getContractInfo() {
        return mContractInfo;
    }

    public LiveData<List<ProjectContractPaymentBean>> getContractPaymentList() {
        return mContractPaymentList;
    }

    public LiveData<List<FileBean>> getFileList() {
        return fileList;
    }

    public void loadData() {
        loadProjectContract();
        loadProjectContractPayment();
    }

    /**
     * 合同信息
     */
    private void loadProjectContract() {

        HttpRequest.getInstance()
                .queryProContractInfo(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<ProjectContractInfoBean>>() {
                    @Override
                    public void success(List<ProjectContractInfoBean> bean) {
                        if (bean == null || bean.size() < 1) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        mContractInfo.postValue(bean.get(0));
                        setFileList(bean.get(0).getConAccessory());
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }

    private void setFileList(String file) {
        ArrayList<FileBean> arrayList = new ArrayList<>();

        //工作方案
        if (!CommonUtils.isStringEmpty(file)) {
            String[] fileData = file.split(",");
            for (int i = 0; i < fileData.length; i++) {
                arrayList.add( new FileBean(1, "",fileData[i], "合同附件", 0));
            }
        }
        fileList.postValue(arrayList);
    }

    /**
     * 合同款项
     */
    private void loadProjectContractPayment() {
        HttpRequest.getInstance()
                .queryProContractPayment(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<ProjectContractPaymentBean>>() {
                    @Override
                    public void success(List<ProjectContractPaymentBean> bean) {
                        mContractPaymentList.postValue(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }
}
