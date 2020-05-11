package com.example.gqsystem.ui.company.material;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/3/31 13:06
 * @desc :
 */
public class CompanyMaterialViewModel extends BaseViewModel {

    private String id;

    private MutableLiveData<List<Object>> mMaterialList;

    public CompanyMaterialViewModel() {
        mMaterialList = new MediatorLiveData<>();
    }

    public LiveData<List<Object>> getRelationCompany() {
        return mMaterialList;
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
     * 获取关联企业列表
     */
    private void loadRelationCompany() {
        HttpRequest.getInstance()
                .queryCompanyMaterial(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<Object>>() {
                    @Override
                    public void success(List<Object> bean) {
                        if (bean.isEmpty()){
                            loadState.postValue(LoadState.NO_DATA);
                        }else {
                            loadState.postValue(LoadState.SUCCESS);
                            mMaterialList.postValue(bean);
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
