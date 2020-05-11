package com.example.gqsystem.ui.main.leaderstroke;

import com.example.gqsystem.MainActivity;
import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.common.CommonDialog;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;

/**
 * @author devel
 * 领导行程
 */
public class LeaderStrokeViewModel extends BaseViewModel {

    private int mPage = 1;

    private MutableLiveData<LeaderActivityListBean> mLeaderActivityList;
    private List<LeaderActivityListBean.RecordsBean> mList;


    public LeaderStrokeViewModel() {
        mLeaderActivityList = new MediatorLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<LeaderActivityListBean> getLeaderActivity() {
        return mLeaderActivityList;
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
     * 获取ling到动态列表
     */
    private void loadLeaderActivity() {
        HttpRequest.getInstance()
                .getLeaderActivity(mPage, Constants.PageSize)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<LeaderActivityListBean>() {
                    @Override
                    public void success(LeaderActivityListBean bean) {

                        if (bean.getTotal() == 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        if (mPage == 1) {
                            mList.clear();
                            mList.addAll(bean.getRecords());
                            mLeaderActivityList.postValue(bean);
                        } else {
                            mList.addAll(bean.getRecords());
                            bean.setRecords(mList);
                            mLeaderActivityList.postValue(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                    }
                });
    }

    /**
     * 删除活动提示
     *
     * @param id
     */
    public void delete(String id) {
        CommonDialog dialog = CommonDialog.newInstance("删除", "是否要删除该动态？");
        dialog.show(((MainActivity) MyActivityManager.getInstance().getCurrentActivity()).getSupportFragmentManager(), "");
        dialog.setConfirmClickListener(() -> deleteActivity(id));
    }

    /**
     * 删除某个活动
     *
     * @param id
     */
    private void deleteActivity(String id) {
        HttpRequest.getInstance()
                .deleteLeaderActivity(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<Response<Void>>() {
                    @Override
                    public void success(Response<Void> bean) {
                        ToastUtils.showToast("删除成功！");
                        loadData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showLongToast("删除失败！请重试");
                    }
                });
    }
}