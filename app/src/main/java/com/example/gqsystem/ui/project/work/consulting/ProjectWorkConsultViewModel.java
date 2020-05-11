package com.example.gqsystem.ui.project.work.consulting;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.ProjectConsultInfo;
import com.example.gqsystem.bean.response.ProjectConsultOtherInfo;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/4/3 13:37
 * @desc :
 */
public class ProjectWorkConsultViewModel extends BaseViewModel {


    private String id;
    private MutableLiveData<List<ProjectConsultInfo>> mWorkList;
    private MutableLiveData<List<ProjectConsultOtherInfo>> mWorkOtherList;

    private int ConsultSize = -1;
    private int ConsultOtherSize = -1;

    public ProjectWorkConsultViewModel() {
        mWorkList = new MutableLiveData<>();
        mWorkOtherList = new MutableLiveData<>();
    }


    public void setId(String id) {
        this.id = id;
    }

    public LiveData<List<ProjectConsultInfo>> getWorkInfo() {
        return mWorkList;
    }

    public LiveData<List<ProjectConsultOtherInfo>> getWorkOtherInfo() {
        return mWorkOtherList;
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
                .queryProConsulting(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<ProjectConsultInfo>>() {
                    @Override
                    public void success(List<ProjectConsultInfo> bean) {
                        if (bean == null || bean.size() <= 0) {
                            ConsultSize = 0;
                            if (ConsultOtherSize==0){
                                loadState.postValue(LoadState.NO_DATA);
                            }
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

        HttpRequest.getInstance()
                .queryProConsultingOther(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<ProjectConsultOtherInfo>>() {
                    @Override
                    public void success(List<ProjectConsultOtherInfo> bean) {
                        if (bean == null || bean.size() <= 0) {
                            ConsultOtherSize = 0;
                            if (ConsultSize==0){
                                loadState.postValue(LoadState.NO_DATA);
                            }
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        mWorkOtherList.postValue(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });

    }
}
