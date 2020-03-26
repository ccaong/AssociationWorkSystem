package com.example.gqsystem.ui.company.constant;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.CompanyContactBean;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class CompanyConstantViewModel extends BaseViewModel {

    private String id;

    private MutableLiveData<List<CompanyContactBean>> mPersonList;

    public CompanyConstantViewModel() {
        mPersonList = new MediatorLiveData<>();
    }

    public LiveData<List<CompanyContactBean>> getPersonList() {
        return mPersonList;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 重新加载
     */
    @Override
    public void reloadData() {
        loadData();
    }

    /**
     * 第一次加载数据
     */
    public void loadData() {
        loadState.postValue(LoadState.LOADING);
        loadPersonListData();
    }


    /**
     * 获取评审人员列表
     */
    private void loadPersonListData() {
        HttpRequest.getInstance()
                .queryCompanyUserByMainId(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<CompanyContactBean>>() {
                    @Override
                    public void success(List<CompanyContactBean> bean) {
                        if (bean.isEmpty()) {
                            loadState.postValue(LoadState.NO_DATA);
                        } else {
                            loadState.postValue(LoadState.SUCCESS);
                            mPersonList.postValue(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }
}
