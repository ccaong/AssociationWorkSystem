package com.example.gqsystem.ui.home.homepage;

import com.example.gqsystem.R;
import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.HomePageBean;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class HomePageViewModel extends BaseViewModel {

    private MutableLiveData<List<HomePageBean>> homePageBeanList;
    private MutableLiveData<List<Integer>> homeBannerList;

    public HomePageViewModel() {
        homeBannerList = new MutableLiveData<>();
        homePageBeanList = new MutableLiveData<>();
    }

    public LiveData<List<Integer>> getBannerList() {
        return homeBannerList;
    }

    public LiveData<List<HomePageBean>> getHomeList() {
        return homePageBeanList;
    }

    public void initData() {
        initHomePageList();
        initHomeBannerList();
    }


    private void initHomePageList() {

        List<HomePageBean> mList = new ArrayList<>();

        mList.add(new HomePageBean("1", "待开发", 0));
        mList.add(new HomePageBean("2", "进行中", 0));
        mList.add(new HomePageBean("3", getResources().getString(R.string.project_name_1), 0));
        mList.add(new HomePageBean("4", getResources().getString(R.string.project_name_2), 0));
        mList.add(new HomePageBean("5", getResources().getString(R.string.project_name_3), 0));
        mList.add(new HomePageBean("6", getResources().getString(R.string.project_name_4), 0));
        mList.add(new HomePageBean("7", getResources().getString(R.string.project_name_5), 0));
        mList.add(new HomePageBean("9", getResources().getString(R.string.project_name_7), 0));
        mList.add(new HomePageBean("8", getResources().getString(R.string.project_name_6), 0));
        mList.add(new HomePageBean("10", getResources().getString(R.string.project_name_8), 0));

        homePageBeanList.postValue(mList);
    }

    private void initHomeBannerList() {
        List<Integer> mList = new ArrayList<>();

        mList.add(R.mipmap.home_banner_first);
        mList.add(R.mipmap.home_banner_second);
        homeBannerList.postValue(mList);
    }
}
