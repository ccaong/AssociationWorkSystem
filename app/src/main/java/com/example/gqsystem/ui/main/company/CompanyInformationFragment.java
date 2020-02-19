package com.example.gqsystem.ui.main.company;

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
 * @author devel
 * 企业基本信息
 */
public class CompanyInformationFragment extends Fragment {

    private CompanyInformationViewModel mViewModel;

    public static CompanyInformationFragment newInstance() {
        return new CompanyInformationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.company_information_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CompanyInformationViewModel.class);
        // TODO: Use the ViewModel
    }

}
