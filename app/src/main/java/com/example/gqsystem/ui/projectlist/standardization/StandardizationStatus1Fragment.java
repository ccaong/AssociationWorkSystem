package com.example.gqsystem.ui.projectlist.standardization;

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
public class StandardizationStatus1Fragment extends Fragment {


    private int type;

    public static StandardizationStatus1Fragment newInstance(int type) {
        StandardizationStatus1Fragment standardizationStatus1Fragment = new StandardizationStatus1Fragment();
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

        View root = inflater.inflate(R.layout.fragment_standardization1, container, false);

        switch (type) {
            case 1:
                root = inflater.inflate(R.layout.fragment_standardization1, container, false);
                break;
            case 2:
                root = inflater.inflate(R.layout.fragment_standardization2, container, false);
                break;
            case 3:
                root = inflater.inflate(R.layout.fragment_standardization3, container, false);
                break;
            case 4:
                root = inflater.inflate(R.layout.fragment_standardization4, container, false);
                break;
            case 5:
                root = inflater.inflate(R.layout.fragment_standardization5, container, false);
                break;
            case 6:
                root = inflater.inflate(R.layout.fragment_standardization6, container, false);
                break;
        }
        return root;
    }
}
