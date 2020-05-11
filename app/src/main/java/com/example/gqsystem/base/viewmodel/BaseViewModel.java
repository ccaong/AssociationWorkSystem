package com.example.gqsystem.base.viewmodel;


import android.content.res.Resources;
import android.util.Log;


import com.example.gqsystem.App;
import com.example.gqsystem.MainActivity;
import com.example.gqsystem.R;
import com.example.gqsystem.common.CommonDialog;
import com.example.gqsystem.enums.DownLoadState;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.interfice.DownloadListener;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.util.DownloadUtil;
import com.example.gqsystem.util.FileOpenUtil;
import com.example.gqsystem.util.FileUtil;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel的基类
 *
 * @author devel
 */
public abstract class BaseViewModel extends ViewModel implements DefaultLifecycleObserver {

    public Resources resources;

    /**
     * 加载状态
     */
    public MutableLiveData<LoadState> loadState = new MutableLiveData<>();

    public MutableLiveData<String> errorMsg = new MutableLiveData<>(getResources().getString(R.string.load_error));


    /**
     * 是否为刷新数据
     */
    public boolean mRefresh;

    private MutableLiveData<Integer> mProgress = new MutableLiveData<>();

    private MutableLiveData<DownLoadState> mDownloadState = new MutableLiveData<>();

    /**
     * 重新加载数据。没有网络，点击重试时回调
     */
    public void reloadData() {

    }

    public Resources getResources() {
        if (resources == null) {
            resources = App.getContext().getResources();
        }
        return resources;
    }

    /**
     * 判断本地有没有该文件
     *
     * @param fileName
     */
    public void isFileExists(String fileName) {
        if (FileUtil.fileIsDownload(fileName)) {
            downLoad(fileName);
        } else {
            CommonDialog dialog = CommonDialog.newInstance("下载", "文件还未下载，是否要下载该文档？");
            dialog.show(((MainActivity) MyActivityManager.getInstance().getCurrentActivity()).getSupportFragmentManager(), "");
            dialog.setConfirmClickListener(() -> downLoad(fileName));
        }
    }


    /**
     * 下载文件
     *
     * @param filePath
     */
    private void downLoad(String filePath) {
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.downloadFile(filePath, new DownloadListener() {
            @Override
            public void onStart() {
                mProgress.postValue(1);
                Log.e("下载", "onStart");
                mDownloadState.postValue(DownLoadState.ON_START);
            }

            @Override
            public void onProgress(int currentLength) {
                Log.e("下载", "onProgress" + currentLength);
                mDownloadState.postValue(DownLoadState.DOWNLOADING);
                mProgress.postValue(currentLength);
            }

            @Override
            public void onFinish(String localPath) {
                Log.e("下载", "onFinish" + localPath);
                mProgress.postValue(0);
                FileOpenUtil fileOpenUtil = new FileOpenUtil();
                fileOpenUtil.openFile(localPath);
                mDownloadState.postValue(DownLoadState.DOWNLOAD_SUCCESS);
            }

            @Override
            public void onFailure(String errorMsg) {
                mProgress.postValue(0);
                Log.e("下载", "onFailure" + errorMsg);
                //下载失败，删除下载的文件
                FileUtil.deleteFile(filePath);
                mDownloadState.postValue(DownLoadState.DOWNLOAD_FAIL);
            }
        });
    }

    /**
     * 获取下载进度
     *
     * @return 下载进度
     */
    public LiveData<Integer> getDownLoadProgress() {
        return mProgress;
    }


    /**
     * 获取下载状态
     *
     * @return 下载状态
     */
    public LiveData<DownLoadState> getDownLoadState() {
        return mDownloadState;
    }

}
