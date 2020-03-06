package com.example.gqsystem.ui.metting.list;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.enums.LoadState;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class MeetingViewModel extends BaseViewModel {


    private int mPage = 0;

    private MutableLiveData<Object> mArticleList;
    private List<Object> mList;

    public MeetingViewModel() {
        mArticleList = new MediatorLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<Object> getArticleList() {
        return mArticleList;
    }


    /**
     * 刷新
     */
    public void refreshData(Boolean refresh) {
        if (refresh) {
            mPage = 0;
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

        mPage = 0;
        mRefresh = false;
        loadMeetingList();
    }

    private void loadMeetingList() {
        loadState.postValue(LoadState.NO_DATA);

    }

}
