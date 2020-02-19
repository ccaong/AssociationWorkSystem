package com.example.gqsystem.ui.main.bottominquiry;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gqsystem.R;

/**
 * 搜索
 *
 * @author devel
 */
public class InquiryFragment extends Fragment {

    private InquiryViewModel mViewModel;

    public static InquiryFragment newInstance() {
        return new InquiryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inquiry, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InquiryViewModel.class);
        // TODO: Use the ViewModel
    }

}
