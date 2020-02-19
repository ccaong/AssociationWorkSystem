package com.example.gqsystem.ui.main.leaderstroke;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author devel
 * 领导行程
 */
public class LeaderStrokeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LeaderStrokeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("领导行程");
    }

    public LiveData<String> getText() {
        return mText;
    }
}