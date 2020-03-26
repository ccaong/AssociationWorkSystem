package com.example.gqsystem.ui.main.leaderstroke;

import android.util.Log;

import com.example.gqsystem.MainActivity;
import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.common.CommonDialog;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.interfice.DownloadListener;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.util.DownloadUtil;
import com.example.gqsystem.util.FileOpenUtil;
import com.example.gqsystem.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 * 领导行程
 */
public class LeaderStrokeViewModel extends BaseViewModel {

    private int mPage = 1;

    private MutableLiveData<LeaderActivityListBean> mLeaderActivityList;
    private List<LeaderActivityListBean.RecordsBean> mList;

    private MutableLiveData<Integer> mProgress;


    public LeaderStrokeViewModel() {
        mLeaderActivityList = new MediatorLiveData<>();
        mProgress = new MediatorLiveData<>();
        mList = new ArrayList<>();
    }

    public LiveData<LeaderActivityListBean> getLeaderActivity() {
        return mLeaderActivityList;
    }

    public LiveData<Integer> getDownLoadProgress() {
        return mProgress;
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
                .getLeaderActivity(mPage, 10)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<LeaderActivityListBean>() {
                    @Override
                    public void success(LeaderActivityListBean bean) {

                        if (bean.getPages() == 0) {
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
     * 判断本地有没有该文件
     *
     * @param filePath
     */
    public void isFileExists(String filePath) {
        if (FileUtil.fileIsDownload(filePath)) {
            downLoad(filePath);
        } else {
            CommonDialog dialog = CommonDialog.newInstance("下载", "文件还未下载，是否要下载该文档？");
            dialog.show(((MainActivity)MyActivityManager.getInstance().getCurrentActivity()).getSupportFragmentManager(),"");
            dialog.setConfirmClickListener(() -> downLoad(filePath));
        }
    }


    /**
     * 下载文件
     *
     * @param filePath
     */
    private void downLoad(String filePath) {
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.downloadFile(FileUtil.getFileName(filePath), new DownloadListener() {
            @Override
            public void onStart() {
                Log.e("请求", "onStart");
            }

            @Override
            public void onProgress(int currentLength) {
                Log.e("请求", "onProgress" + currentLength);
                mProgress.postValue(currentLength);
            }

            @Override
            public void onFinish(String localPath) {
                Log.e("请求", "onFinish" + localPath);
                FileOpenUtil fileOpenUtil = new FileOpenUtil();
                fileOpenUtil.openFile(localPath);
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e("请求", "onFailure" + errorMsg);
            }
        });
    }
}