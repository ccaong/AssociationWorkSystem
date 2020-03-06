package com.example.gqsystem.ui.activity.splash;

import com.example.gqsystem.base.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * @author : devel
 * @date : 2020/2/24 9:33
 * @desc :
 */
public class SplashViewModel extends BaseViewModel {

    private MutableLiveData<String> mTime;


    public SplashViewModel() {
        mTime = new MutableLiveData<>();
    }

    public LiveData<String> getTime() {
        return mTime;
    }


    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        startTime();
    }

    private void startTime() {


        List<String> list = new ArrayList<>();
        for (int i = 3; i >= 0; i--) {
            list.add(i + "");
        }

        Observable<String> observable = Observable.fromIterable(list);
        Observable<Long> time = Observable.interval(1, TimeUnit.SECONDS);

        Observable.zip(observable, time, new BiFunction<String, Long, String>() {
            @Override
            public String apply(String s, Long aLong) throws Exception {
                return s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String str) throws Exception {
                if ("0".equals(str)) {
                    mTime.postValue(str);
                }
            }
        });
    }
}
