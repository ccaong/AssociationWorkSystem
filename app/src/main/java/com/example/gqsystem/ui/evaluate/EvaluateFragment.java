package com.example.gqsystem.ui.evaluate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gqsystem.R;

public class EvaluateFragment extends Fragment {


    private int type;

    public static EvaluateFragment newInstance(int type) {
        EvaluateFragment standardizationStatus1Fragment = new EvaluateFragment();
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

        View root = inflater.inflate(R.layout.fragment_evaluate1, container, false);

        switch (type) {
            case 1:
                root = inflater.inflate(R.layout.fragment_evaluate1, container, false);
                break;
            case 2:
                root = inflater.inflate(R.layout.fragment_evaluate2, container, false);
                break;
        }
        return root;
    }
}
