package com.example.gqsystem.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gqsystem.R;
import com.example.gqsystem.ui.activity.ProjectDoingActivity;
import com.example.gqsystem.ui.activity.ProjectToDoActivity;
import com.example.gqsystem.ui.projectlist.doubleprevention.DoublePreventionActivity;
import com.example.gqsystem.ui.projectlist.evaluate.EvaluateActivity;
import com.example.gqsystem.ui.projectlist.govproject.GovProjectActivity;
import com.example.gqsystem.ui.projectlist.informationize.InformationizeActivity;
import com.example.gqsystem.ui.projectlist.securitymanagement.SecurityManagementActivity;
import com.example.gqsystem.ui.projectlist.specialrectification.SpecialRectificationActivity;
import com.example.gqsystem.ui.projectlist.standardization.StandardizationActivity;
import com.example.gqsystem.util.ActivitySkipUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author devel
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.search_bar)
    SearchView searchBar;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.tv_9)
    TextView tv9;
    @BindView(R.id.tv_10)
    TextView tv10;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;


    }

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6, R.id.tv_7, R.id.tv_8, R.id.tv_9, R.id.tv_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                ActivitySkipUtil.skipActivity(getActivity(), ProjectToDoActivity.class);
                break;
            case R.id.tv_2:
                ActivitySkipUtil.skipActivity(getActivity(), ProjectDoingActivity.class);
                break;
            case R.id.tv_3:
                ActivitySkipUtil.skipActivity(getActivity(), StandardizationActivity.class);
                break;
            case R.id.tv_4:
                ActivitySkipUtil.skipActivity(getActivity(), DoublePreventionActivity.class);
                break;
            case R.id.tv_5:
                ActivitySkipUtil.skipActivity(getActivity(), EvaluateActivity.class);
                break;
            case R.id.tv_6:
                ActivitySkipUtil.skipActivity(getActivity(), SecurityManagementActivity.class);
                break;
            case R.id.tv_7:
                ActivitySkipUtil.skipActivity(getActivity(), InformationizeActivity.class);
                break;
            case R.id.tv_8:
                ActivitySkipUtil.skipActivity(getActivity(), SpecialRectificationActivity.class);
                break;
            case R.id.tv_9:
                ActivitySkipUtil.skipActivity(getActivity(), GovProjectActivity.class);
                break;
            case R.id.tv_10:
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
