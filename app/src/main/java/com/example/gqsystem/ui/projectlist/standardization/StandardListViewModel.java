package com.example.gqsystem.ui.projectlist.standardization;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.enums.LoadState;

import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class StandardListViewModel extends BaseViewModel {

    private int type;

    private int mPage = 0;

    private MutableLiveData<Object> mList;

    public StandardListViewModel() {

    }

    public void setType(int type) {
        this.type = type;
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
        loadProjectList();
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
        loadProjectList();
    }


    private void loadProjectList() {

    }


}
