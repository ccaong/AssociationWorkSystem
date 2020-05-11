package com.example.gqsystem.ui.project;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.CompanyPerInfoBean;
import com.example.gqsystem.bean.response.ProjectListBean;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/4/1 14:07
 * @desc : 项目详细信息
 */
public class ProjectContentViewModel extends BaseViewModel {

    private MutableLiveData<ProjectListBean.RecordsBean> mProjectConstant;
    private MutableLiveData<List<FileBean>> mFilePlanList;

    private MutableLiveData<CompanyPerInfoBean> mCompanyPer;

    public ProjectContentViewModel() {
        mProjectConstant = new MutableLiveData<>();
        mFilePlanList = new MutableLiveData<>();
        mCompanyPer = new MutableLiveData<>();
    }

    public void setProjectConstant(ProjectListBean.RecordsBean bean) {
        this.mProjectConstant.postValue(bean);
        initFileData(bean);
    }

    private void initFileData(ProjectListBean.RecordsBean bean) {
        ArrayList<FileBean> arrayList = new ArrayList<>();

        //工作方案
        if (bean.getWorkPlan() != null) {
            String[] fileData = bean.getWorkPlan().split(",");
            for (int i = 0; i < fileData.length; i++) {
                arrayList.add(new FileBean(i, "工作方案", fileData[i], "工作方案", 0));
            }
        }
        //建设方案
        if (bean.getBuildPlan() != null) {
            String[] fileData = bean.getBuildPlan().split(",");
            for (int i = 0; i < fileData.length; i++) {
                arrayList.add(new FileBean(i, "建设方案", fileData[i], "建设方案", 0));
            }
            mFilePlanList.postValue(arrayList);
        }
    }

    /**
     * 获取某一个项目信息
     *
     * @return
     */
    public LiveData<ProjectListBean.RecordsBean> getProjectConstant() {
        return mProjectConstant;
    }

    /**
     * 方案
     *
     * @return
     */
    public LiveData<List<FileBean>> getFilePlan() {
        return mFilePlanList;
    }

    /**
     * 企业对接联系人
     *
     * @return
     */
    public LiveData<CompanyPerInfoBean> getCompanyPer() {
        return mCompanyPer;
    }

    /**
     * 企业对接联系人
     */
    public void loadCompanyPer(String id) {
        HttpRequest.getInstance()
                .queryCompanyUser(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<CompanyPerInfoBean>() {
                    @Override
                    public void success(CompanyPerInfoBean bean) {
                        if (bean == null) {
                            ToastUtils.showToast("无法获取到该联系人的详细信息");
                            return;
                        }
                        mCompanyPer.postValue(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast("无法获取到该联系人的详细信息");
                    }
                });
    }
}
