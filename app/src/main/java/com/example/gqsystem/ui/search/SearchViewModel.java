package com.example.gqsystem.ui.search;

import com.example.gqsystem.App;
import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.SearchHistoryBean;
import com.example.gqsystem.bean.response.ProjectListBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.manager.ThreadManager;
import com.example.gqsystem.room.AbstractDataBase;
import com.example.gqsystem.room.dao.SearchHistoryDao;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class SearchViewModel extends BaseViewModel {

    private ThreadManager.ThreadPool threadPool = ThreadManager.getThreadPool();
    private SearchHistoryDao searchHistoryDao;

    private int mPage = 1;
    private String searchString;
    /**
     * 分类
     */
    public MutableLiveData<Integer> type;

    private MutableLiveData<List<SearchHistoryBean>> mSearchList;

    private MutableLiveData<ProjectListBean> mProjectList;
    private List<ProjectListBean.RecordsBean> mList;

    public SearchViewModel() {
        type = new MutableLiveData<>(1);
        mSearchList = new MutableLiveData<>();
        mProjectList = new MutableLiveData<>();
        mList = new ArrayList<>();
        init();
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
     * 获取搜索历史列表
     *
     * @return
     */
    public LiveData<List<SearchHistoryBean>> getSearchHistoryList() {
        return mSearchList;
    }

    /**
     * 初始化线程池等操作
     */
    private void init() {
        AbstractDataBase appDataBase = AbstractDataBase.getInstance(App.getContext());
        searchHistoryDao = appDataBase.getHistoryDao();
        loadSearchHistory();
    }

    /**
     * 查询所有的历史数据
     */
    private void loadSearchHistory() {
        threadPool.execute(() -> mSearchList.postValue(searchHistoryDao.getAll()));
    }

    /**
     * 插入一条数据
     *
     * @param searchHistoryBean
     */
    public void insertData(SearchHistoryBean searchHistoryBean) {
        threadPool.execute(() -> searchHistoryDao.insertSearchHistory(searchHistoryBean));
        loadSearchHistory();
    }

    /**
     * 删除历史数据
     */
    public void deleteHistory() {
        threadPool.execute(() -> {
            searchHistoryDao.deleteAll();
            mSearchList.postValue(null);
        });

    }

    /**
     * 设置搜索内容
     *
     * @param text
     */
    public void setSearchText(String text) {
        searchString = "*" + text + "*";
        loadData();
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
     * 搜索
     */
    private void loadProjectList() {

        String comName = "";
        String projectPer = "";
        String marketPer = "";

        switch (type.getValue()) {
            case 1:
                comName = searchString;
                break;
            case 2:
                projectPer = searchString;
                break;
            case 3:
                marketPer = searchString;
                break;
            default:
                break;
        }

        HttpRequest.getInstance()
                .searchProjectInfoList(mPage, Constants.PageSize, comName, marketPer, projectPer)
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
