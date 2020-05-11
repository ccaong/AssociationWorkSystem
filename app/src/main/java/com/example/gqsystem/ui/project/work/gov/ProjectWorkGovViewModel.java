package com.example.gqsystem.ui.project.work.gov;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.ProjectGovInfo;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/4/3 11:39
 * @desc :
 */
public class ProjectWorkGovViewModel extends BaseViewModel {


    private String id;
    private MutableLiveData<List<ProjectGovInfo>> mWorkList;

    public ProjectWorkGovViewModel() {
        mWorkList = new MutableLiveData<>();
    }


    public void setId(String id) {
        this.id = id;
    }

    public LiveData<List<ProjectGovInfo>> getWorkInfo() {
        return mWorkList;
    }


    /**
     * 刷新
     */
    public void refreshData() {
        loadProjectWorkInfo();
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
        loadProjectWorkInfo();
    }

    /**
     * 工作过程信息
     */
    private void loadProjectWorkInfo() {
        HttpRequest.getInstance()
                .queryProGovernment(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<ProjectGovInfo>>() {
                    @Override
                    public void success(List<ProjectGovInfo> bean) {
                        if (bean == null || bean.size() <= 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        mWorkList.postValue(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }
}
