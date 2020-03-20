package com.example.gqsystem.ui.main.leaderstroke;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
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
 * 领导行程
 */
public class LeaderStrokeViewModel extends BaseViewModel {


    private int mPage = 1;

    private MutableLiveData<LeaderActivityListBean> mLeadeActivtyList;
    private List<LeaderActivityListBean.RecordsBean> mList;

    public LeaderStrokeViewModel() {
        mLeadeActivtyList = new MediatorLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<LeaderActivityListBean> getLeaderActivity() {
        return mLeadeActivtyList;
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
        loadLeaderActivity();
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
        loadLeaderActivity();
    }


    /**
     * 获取ling到动态列表
     */
    private void loadLeaderActivity() {
        HttpRequest.getInstance()
                .getLeaderActivity(mPage, 10)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<LeaderActivityListBean>() {
                    @Override
                    public void success(LeaderActivityListBean bean) {

                        if (bean.getPages() == 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        if (mPage == 1) {
                            mList.clear();
                            mList.addAll(bean.getRecords());
                            mLeadeActivtyList.postValue(bean);
                        } else {
                            mList.addAll(bean.getRecords());
                            bean.setRecords(mList);
                            mLeadeActivtyList.postValue(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }
}