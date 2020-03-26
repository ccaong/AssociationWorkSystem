package com.example.gqsystem.ui.company.related;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.CompanyRelatedBean;
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
public class CompanyRelatedViewModel extends BaseViewModel {

    private String id;

    private MutableLiveData<List<CompanyRelatedBean>> mRelationCompany;

    public CompanyRelatedViewModel() {
        mRelationCompany = new MediatorLiveData<>();
    }

    public LiveData<List<CompanyRelatedBean>> getRelationCompany() {
        return mRelationCompany;
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
        loadRelationCompany();
    }


    /**
     * 获取评审人员列表
     */
    private void loadRelationCompany() {
        HttpRequest.getInstance()
                .queryRelationCompanyByMainId(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<CompanyRelatedBean>>() {
                    @Override
                    public void success(List<CompanyRelatedBean> bean) {
                        if (bean.isEmpty()){
                            loadState.postValue(LoadState.NO_DATA);
                        }else {
                            loadState.postValue(LoadState.SUCCESS);
                            mRelationCompany.postValue(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                        errorMsg.postValue(e.getMessage());
                    }
                });
    }
}
