package com.example.gqsystem.ui.metting.list;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
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
public class MeetingViewModel extends BaseViewModel {


    private int mPage = 1;

    private MutableLiveData<Object> mMeetingList;
    private List<Object> mList;

    public MeetingViewModel() {
        mMeetingList = new MediatorLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<Object> getMeetingList() {
        return mMeetingList;
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
        loadMeetingList();
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
        loadMeetingList();
    }

    private void loadMeetingList() {
        HttpRequest.getInstance()
                .queryMeetingList(mPage, 10)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<Object>() {
                    @Override
                    public void success(Object bean) {
//
//                        if (bean.getPages() == 0) {
                        loadState.postValue(LoadState.NO_DATA);
//                            return;
//                        }
//                        loadState.postValue(LoadState.SUCCESS);
//                        if (mPage == 1) {
//                            mList.clear();
//                            mList.addAll(bean.getRecords());
//                            mLeadeActivtyList.postValue(bean);
//                        } else {
//                            mList.addAll(bean.getRecords());
//                            bean.setRecords(mList);
//                            mLeadeActivtyList.postValue(bean);
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                        errorMsg.postValue(e.getMessage());
                    }
                });
    }
}
