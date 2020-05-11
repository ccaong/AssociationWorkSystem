package com.example.gqsystem.ui.datashare;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.DataShareListBean;
import com.example.gqsystem.config.Constants;
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
public class DataShareViewModel extends BaseViewModel {


    private int mPage = 1;

    private MutableLiveData<DataShareListBean> mDataShareList;
    private List<DataShareListBean.RecordsBean> mList;


    private String searchString;

    public DataShareViewModel() {
        mDataShareList = new MediatorLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<DataShareListBean> getDataShareList() {
        return mDataShareList;
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
     * 获取分享的文件列表
     */
    private void loadLeaderActivity() {
        HttpRequest.getInstance()
                .queryDataShareList(mPage, Constants.PageSize, searchString)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<DataShareListBean>() {
                    @Override
                    public void success(DataShareListBean bean) {

                        if (bean.getTotal() == 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        if (mPage == 1) {
                            mList.clear();
                        }
                        setFileList(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }

    private void setFileList(DataShareListBean bean) {
        for (DataShareListBean.RecordsBean recordsBean : bean.getRecords()) {
            if (recordsBean.getDataFile().contains(",")) {
                String[] files = recordsBean.getDataFile().split(",");
                for (String fileName : files) {
                    DataShareListBean.RecordsBean data = new DataShareListBean.RecordsBean();
                    data.setRecordsBean(recordsBean);
                    data.setDataFile(fileName);
                    mList.add(data);
                }
            } else {
                mList.add(recordsBean);
            }
        }
        bean.setRecords(mList);
        mDataShareList.postValue(bean);
    }
}
