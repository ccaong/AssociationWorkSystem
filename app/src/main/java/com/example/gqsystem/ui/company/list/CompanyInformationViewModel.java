package com.example.gqsystem.ui.company.list;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.enums.LoadState;


/**
 * @author devel
 */
public class CompanyInformationViewModel extends BaseViewModel {

    private int mPage = 0;

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
        loadCompanyList();
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
        loadCompanyList();
    }


    private void loadCompanyList() {

    }

}
