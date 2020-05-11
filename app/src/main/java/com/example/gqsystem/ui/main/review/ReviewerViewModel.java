package com.example.gqsystem.ui.main.review;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.ReviewerListBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;


/**
 * @author devel
 */
public class ReviewerViewModel extends BaseViewModel {

    private int mPage = 1;

    private MutableLiveData<ReviewerListBean> mPersonList;
    private List<ReviewerListBean.RecordsBean> mList;

    private MutableLiveData<ReviewerListBean.RecordsBean> mRecordsBean;
    private MutableLiveData<List<FileBean>> mFileList;


    public ReviewerViewModel() {
        mPersonList = new MediatorLiveData<>();
        mList = new ArrayList<>();
        mRecordsBean = new MediatorLiveData<>();
        mFileList = new MediatorLiveData<>();
    }

    public LiveData<ReviewerListBean> getPersonList() {
        return mPersonList;
    }

    public LiveData<List<FileBean>> getFileList() {
        return mFileList;
    }


    public void setReviewer(ReviewerListBean.RecordsBean bean) {
        mRecordsBean.postValue(bean);

        setFileList(bean.getAccessory());
    }

    private void setFileList(String file) {
        ArrayList<FileBean> arrayList = new ArrayList<>();

        //工作方案
        if (!CommonUtils.isStringEmpty(file)) {
            String[] fileData = file.split(",");
            for (int i = 0; i < fileData.length; i++) {
                arrayList.add(new FileBean(1, "", fileData[i], "附件", 0));
            }
        }
        mFileList.postValue(arrayList);
    }

    public LiveData<ReviewerListBean.RecordsBean> getReviewer() {
        return mRecordsBean;
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
                .getReviewerList(mPage, Constants.PageSize)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<ReviewerListBean>() {
                    @Override
                    public void success(ReviewerListBean bean) {

                        if (bean.getTotal() == 0) {
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
