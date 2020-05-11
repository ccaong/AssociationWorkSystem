package com.example.gqsystem.ui.project.work.judge;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.ProjectJudgeInfoBean;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/4/7 10:05
 * @desc :
 */
public class ProjectWorkJudgeViewModel extends BaseViewModel {

    private String id;
    private MutableLiveData<ProjectJudgeInfoBean> mWorkBean;

    public MutableLiveData<Integer> progress1;
    public MutableLiveData<Integer> progress2;
    public MutableLiveData<Integer> progress3;
    public MutableLiveData<Integer> progress4;


    public ProjectWorkJudgeViewModel() {
        mWorkBean = new MutableLiveData<>();

        progress1 = new MutableLiveData<>();
        progress2 = new MutableLiveData<>();
        progress3 = new MutableLiveData<>();
        progress4 = new MutableLiveData<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public LiveData<ProjectJudgeInfoBean> getWorkInfo() {
        return mWorkBean;
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
                .queryProJudge(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<ProjectJudgeInfoBean>>() {
                    @Override
                    public void success(List<ProjectJudgeInfoBean> bean) {
                        if (bean == null || bean.size() <= 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        mWorkBean.postValue(bean.get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }
}
