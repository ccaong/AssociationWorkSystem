package com.example.gqsystem.ui.metting.list;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.MeetingListBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class MeetingViewModel extends BaseViewModel {

    private int mPage = 1;

    private MutableLiveData<MeetingListBean> mMeetingList;
    private List<MeetingListBean.RecordsBean> mList;
    private MutableLiveData<MeetingListBean.RecordsBean> mMeetingBean;

    private MutableLiveData<List<FileBean>> mMeetingFileList;

    public MeetingViewModel() {
        mMeetingList = new MediatorLiveData<>();
        mList = new ArrayList<>();
        mMeetingBean = new MediatorLiveData<>();

        mMeetingFileList = new MediatorLiveData<>();
    }

    /**
     * 获取会议列表
     *
     * @return
     */
    public LiveData<MeetingListBean> getMeetingList() {
        return mMeetingList;
    }

    /**
     * 选择某一个会议
     *
     * @param bean
     */
    public void setMeetingBean(MeetingListBean.RecordsBean bean) {
        this.mMeetingBean.postValue(bean);
        ArrayList<FileBean> arrayList = new ArrayList<>();

        //会议通知
        if (bean.getMeetingData() != null) {
            String[] fileData = bean.getMeetingNotice().split(",");
            for (String name : fileData) {
                arrayList.add(new FileBean(name, "会议通知", 0));
            }
        }

        //会议资料
        if (bean.getMeetingData() != null) {
            String[] fileData = bean.getMeetingData().split(",");
            for (String name : fileData) {
                arrayList.add(new FileBean(name, "会议资料", 0));
            }
        }
        //会议纪要
        if (bean.getMeetingSummary() != null) {
            String[] fileSummaryS = bean.getMeetingSummary().split(",");
            for (String name : fileSummaryS) {
                arrayList.add(new FileBean(name, "会议纪要", 0));
            }
        }

        //会议图片
        if (bean.getMeetingPic() != null) {
            String[] fileSummaryS = bean.getMeetingPic().split(",");
            for (String name : fileSummaryS) {
                arrayList.add(new FileBean(name, "会议图片", 0));
            }
        }
        mMeetingFileList.postValue(arrayList);
    }

    /**
     * 获取会议信息
     *
     * @return
     */
    public LiveData<MeetingListBean.RecordsBean> getMeetingBean() {
        return mMeetingBean;
    }


    /**
     * 会议文件
     *
     * @return
     */
    public LiveData<List<FileBean>> getMeetingFileList() {
        return mMeetingFileList;
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
        loadMeetingList();
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
        loadMeetingList();
    }

    private void loadMeetingList() {
        HttpRequest.getInstance()
                .queryMeetingList(mPage, Constants.PageSize)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<MeetingListBean>() {
                    @Override
                    public void success(MeetingListBean bean) {
                        if (bean.getPages() == 0) {
                            loadState.postValue(LoadState.NO_DATA);
                            return;
                        }
                        loadState.postValue(LoadState.SUCCESS);
                        if (mPage == 1) {
                            mList.clear();
                            mList.addAll(bean.getRecords());
                            mMeetingList.postValue(bean);
                        } else {
                            mList.addAll(bean.getRecords());
                            bean.setRecords(mList);
                            mMeetingList.postValue(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.postValue(LoadState.ERROR);
                        errorMsg.postValue(e.getMessage());
                    }
                });
    }
}
