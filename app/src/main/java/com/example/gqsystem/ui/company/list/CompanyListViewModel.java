package com.example.gqsystem.ui.company.list;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.CompanyListBean;
import com.example.gqsystem.bean.response.InvoiceInfoBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;


/**
 * 企业列表
 *
 * @author devel
 */
public class CompanyListViewModel extends BaseViewModel {

    private int mPage = 1;

    private MutableLiveData<CompanyListBean> mCompanyList;
    private List<CompanyListBean.RecordsBean> mList;

    private MutableLiveData<InvoiceInfoBean> mInvoiceInfoBean;

    private String searchString;

    public CompanyListViewModel() {
        mCompanyList = new MediatorLiveData<>();
        mList = new ArrayList<>();
        mInvoiceInfoBean = new MediatorLiveData<>();
    }

    public LiveData<CompanyListBean> getCompanyList() {
        return mCompanyList;
    }

    public LiveData<InvoiceInfoBean> getInvoiceInfoBean() {
        return mInvoiceInfoBean;
    }

    public void setSearchText(String text) {
        searchString = "*" + text + "*";
        loadData();
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

        mPage = 1;
        mRefresh = false;
        loadCompanyList();
    }


    /**
     * 获取企业信息
     */
    private void loadCompanyList() {
        HttpRequest.getInstance()
                .getCompanyList(mPage,  Constants.PageSize, searchString)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<CompanyListBean>() {
                    @Override
                    public void success(CompanyListBean bean) {
                        if (bean.getTotal() == 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        if (mPage == 1) {
                            mList.clear();
                            mList.addAll(bean.getRecords());
                            mCompanyList.postValue(bean);
                        } else {
                            mList.addAll(bean.getRecords());
                            bean.setRecords(mList);
                            mCompanyList.postValue(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }


    /**
     * 获取企业开票信息
     */
    public void loadCompanyList(String id) {
        HttpRequest.getInstance()
                .getCompanyInvoice(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<InvoiceInfoBean>>() {
                    @Override
                    public void success(List<InvoiceInfoBean> bean) {
                        if (bean.size() == 1) {
                            mInvoiceInfoBean.postValue(bean.get(0));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast("系统没有收录该企业的开票信息！");
                    }
                });
    }

}
