package com.example.gqsystem.ui.projectlist.securitymanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gqsystem.R;

/**
 * @author devel
 */
public class SecurityManagementFragment extends Fragment {


    private int type;

    public static SecurityManagementFragment newInstance(int type) {
        SecurityManagementFragment standardizationStatus1Fragment = new SecurityManagementFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        standardizationStatus1Fragment.setArguments(bundle);
        return standardizationStatus1Fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_security_management1, container, false);

        switch (type) {
            case 1:
                root = inflater.inflate(R.layout.fragment_security_management1, container, false);
                break;
            case 2:
                root = inflater.inflate(R.layout.fragment_security_management2, container, false);
                break;
        }
        return root;
    }
}