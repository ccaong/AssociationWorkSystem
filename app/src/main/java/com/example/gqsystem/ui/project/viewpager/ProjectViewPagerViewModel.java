package com.example.gqsystem.ui.project.viewpager;

import com.example.gqsystem.base.viewmodel.BaseViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/4/2 16:08
 * @desc :
 */
public class ProjectViewPagerViewModel extends BaseViewModel {

    private MutableLiveData<String[]> title;

    private String mProType;

    public ProjectViewPagerViewModel() {
        title = new MutableLiveData<>();
    }

    public LiveData<String[]> getTitles() {
        return title;
    }

    public void setType(String type) {
        switch (type) {
            case "标准化":
                title.postValue(new String[]{"待开发", "咨询中", "待评审", "待整改", "待发证", "已结束"});
                break;
            case "双重预防机制":
            case "政府采购":
            case "安全管理体系提升":
                title.postValue(new String[]{"待开发", "咨询中"});
                break;
            case "评估评价":
                title.postValue(new String[]{"待开发", "编制中"});
                break;
            case "安全生产信息化":
                title.postValue(new String[]{"待开发", " 洽谈中", "运维中"});
                break;
            case "专项整治":
                break;
            default:
                break;
        }
    }
}
