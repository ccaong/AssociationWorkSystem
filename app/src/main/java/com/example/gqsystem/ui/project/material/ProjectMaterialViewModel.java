package com.example.gqsystem.ui.project.material;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.ProjectComMaterialBean;
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
 * @date : 2020/4/2 11:22
 * @desc : 企业项目资料
 */
public class ProjectMaterialViewModel extends BaseViewModel {

    private String id;
    private MutableLiveData<List<FileBean>> mFileList;
    private List<FileBean> mList;

    public ProjectMaterialViewModel() {
        mFileList = new MutableLiveData<>();
        mList = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public LiveData<List<FileBean>> getFileList() {
        return mFileList;
    }

    /**
     * 刷新
     */
    public void refreshData() {
        loadComMaterial();
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
        loadComMaterial();
    }

    private void loadComMaterial() {
        HttpRequest.getInstance()
                .queryProComMaterial(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<ProjectComMaterialBean>>() {
                    @Override
                    public void success(List<ProjectComMaterialBean> bean) {
                        if (bean == null || bean.size() <= 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        setData(bean.get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }

    /**
     * 设置数据
     *
     * @param bean
     */
    private void setData(ProjectComMaterialBean bean) {
        mList.clear();
        if (bean.getOrganizationChart() != null) {
            mList.addAll(splitFileList(bean.getOrganizationChart(), "组织架构图"));
        }
        if (bean.getSystemManual() != null) {
            mList.addAll(splitFileList(bean.getSystemManual(), "制度汇编或制度手册"));
        }
        if (bean.getOrganizationChart() != null) {
            mList.addAll(splitFileList(bean.getOrganizationChart(), "操作规程手册"));
        }
        if (bean.getEduTrainingFile() != null) {
            mList.addAll(splitFileList(bean.getEduTrainingFile(), "教育培训课件或视频等资料"));
        }
        if (bean.getEvaluationReport() != null) {
            mList.addAll(splitFileList(bean.getEvaluationReport(), "各类型评价报告"));
        }
        if (bean.getBuildFile() != null) {
            mList.addAll(splitFileList(bean.getBuildFile(), "双重预防机制建设资料"));
        }
        if (bean.getAppraisalReport() != null) {
            mList.addAll(splitFileList(bean.getAppraisalReport(), "重大危险源评价/评估报告"));
        }
        if (bean.getContingencyPlan() != null) {
            mList.addAll(splitFileList(bean.getContingencyPlan(), "安全生产应急预案"));
        }
        if (bean.getAccidentCase() != null) {
            mList.addAll(splitFileList(bean.getAccidentCase(), "本企业事故案例"));
        }
        if (bean.getOtherFile() != null) {
            mList.addAll(splitFileList(bean.getOtherFile(), "其他类型资料"));
        }
        mFileList.postValue(mList);
    }

    private List<FileBean> splitFileList(String fileName, String type) {
        String[] fileData = fileName.split(",");
        ArrayList<FileBean> arrayList = new ArrayList<>(fileData.length);
        for (String name : fileData) {
            arrayList.add(new FileBean(name, type, 0));
        }
        return arrayList;
    }
}
