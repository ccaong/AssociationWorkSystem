package com.example.gqsystem.ui.search;

import com.example.gqsystem.App;
import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.SearchHistoryBean;
import com.example.gqsystem.manager.ThreadManager;
import com.example.gqsystem.room.AbstractDataBase;
import com.example.gqsystem.room.dao.SearchHistoryDao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class SearchViewModel extends BaseViewModel {

    private ThreadManager.ThreadPool threadPool = ThreadManager.getThreadPool();
    private SearchHistoryDao searchHistoryDao;

    /**
     * 分类
     */
    public MutableLiveData<Integer> type;

    private MutableLiveData<List<SearchHistoryBean>> mSearchList;

    public SearchViewModel() {
        type = new MutableLiveData<>(1);
        mSearchList = new MutableLiveData<>();
        init();
    }

    public LiveData<List<SearchHistoryBean>> getSearchHistoryList() {
        return mSearchList;
    }

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
     * 搜索
     *
     * @param content
     */
    public void search(String content) {


    }


}
