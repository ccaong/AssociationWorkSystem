package com.example.gqsystem.ui.main.person;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.PersonListBean;
import com.example.gqsystem.bean.response.ReviewerListBean;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;


/**
 * @author devel
 */
public class PersonViewModel extends BaseViewModel {


    private int mPage = 1;

    private MutableLiveData<PersonListBean> mPersonList;
    private List<PersonListBean.RecordsBean> mList;

    public PersonViewModel() {
        mPersonList = new MediatorLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<PersonListBean> getPersonList() {
        return mPersonList;
    }


    /**
     * 刷新
     */
    public void refreshData(Boolean refresh) {
        if (refresh) {
            mPage = 1;
        } else {
            mPage++;
        }
        mRefresh = true;
        loadPersonListData();
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

        mPage = 1;
        mRefresh = false;
        loadPersonListData();
    }


    /**
     * 获取评审人员列表
     */
    private void loadPersonListData() {
        HttpRequest.getInstance()
                .getPersonList(mPage, 10)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<PersonListBean>() {
                    @Override
                    public void success(PersonListBean bean) {

                        if (bean.getPages() == 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        if (mPage == 1) {
                            mList.clear();
                            mList.addAll(bean.getRecords());
                            mPersonList.postValue(bean);
                        } else {
                            mList.addAll(bean.getRecords());
                            bean.setRecords(mList);
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
