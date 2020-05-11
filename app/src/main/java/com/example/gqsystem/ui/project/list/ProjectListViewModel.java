package com.example.gqsystem.ui.project.list;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.ProjectListBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/4/2 17:00
 * @desc :
 */
public class ProjectListViewModel extends BaseViewModel {

    private int mPage;
    private String proType;
    private String proStatus;
    private String companyId;
    private MutableLiveData<ProjectListBean> mProjectList;
    private List<ProjectListBean.RecordsBean> mList;


    public ProjectListViewModel() {
        mProjectList = new MutableLiveData<>();
        mList = new ArrayList<>();

    }

    public void setProType(String type, String status, String companyId) {
        this.proType = type;
        this.proStatus = status;
        this.companyId = companyId;
    }

    /**
     * 获取项目列表
     *
     * @return
     */
    public LiveData<ProjectListBean> getProjectList() {
        return mProjectList;
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
        mPage = 1;
        mRefresh = false;
        loadProjectList();
    }


    /**
     * 获取列表
     */
    private void loadProjectList() {
        HttpRequest.getInstance()
                .projectInfoListPermission(mPage, Constants.PageSize, proType, proStatus,companyId)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<ProjectListBean>() {
                    @Override
                    public void success(ProjectListBean bean) {

                        if (bean.getTotal() == 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        if (mPage == 1) {
                            mList.clear();
                            mList.addAll(bean.getRecords());
                            mProjectList.postValue(bean);
                        } else {
                            mList.addAll(bean.getRecords());
                            bean.setRecords(mList);
                            mProjectList.postValue(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }

}
