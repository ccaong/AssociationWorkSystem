package com.example.gqsystem.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gqsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangeProjectStatusFragment extends Fragment {

    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.spinner_company_name)
    Spinner spinnerCompanyName;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.tv_project_type_title_1)
    TextView tvProjectTypeTitle1;
    @BindView(R.id.tv_project_type_1)
    TextView tvProjectType1;
    @BindView(R.id.tv_project_name_title_1)
    TextView tvProjectNameTitle1;
    @BindView(R.id.tv_project_name_1)
    TextView tvProjectName1;
    @BindView(R.id.tv_project_leader_title_1)
    TextView tvProjectLeaderTitle1;
    @BindView(R.id.tv_status_1)
    TextView tvStatus1;
    @BindView(R.id.view_2)
    View view2;
    @BindView(R.id.tv_project_type_title_2)
    TextView tvProjectTypeTitle2;
    @BindView(R.id.tv_project_type_2)
    TextView tvProjectType2;
    @BindView(R.id.tv_project_name_title_2)
    TextView tvProjectNameTitle2;
    @BindView(R.id.tv_project_name_2)
    TextView tvProjectName2;
    @BindView(R.id.tv_project_leader_title_2)
    TextView tvProjectLeaderTitle2;
    @BindView(R.id.tv_status_2)
    TextView tvStatus2;
    @BindView(R.id.view_3)
    View view3;
    @BindView(R.id.tv_project_type_title_3)
    TextView tvProjectTypeTitle3;
    @BindView(R.id.tv_project_type_3)
    TextView tvProjectType3;
    @BindView(R.id.tv_project_name_title_3)
    TextView tvProjectNameTitle3;
    @BindView(R.id.tv_project_name_3)
    TextView tvProjectName3;
    @BindView(R.id.tv_project_leader_title_3)
    TextView tvProjectLeaderTitle3;
    @BindView(R.id.tv_status_3)
    TextView tvStatus3;
    @BindView(R.id.view_4)
    View view4;
    @BindView(R.id.tv_project_type_title_4)
    TextView tvProjectTypeTitle4;
    @BindView(R.id.tv_project_type_4)
    TextView tvProjectType4;
    @BindView(R.id.tv_project_name_title_4)
    TextView tvProjectNameTitle4;
    @BindView(R.id.tv_project_name_4)
    TextView tvProjectName4;
    @BindView(R.id.tv_project_leader_title_4)
    TextView tvProjectLeaderTitle4;
    @BindView(R.id.tv_status_4)
    TextView tvStatus4;
    @BindView(R.id.view_5)
    View view5;

    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_change_status, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @OnClick({R.id.tv_status_1, R.id.tv_status_2, R.id.tv_status_3, R.id.tv_status_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status_1:
            case R.id.tv_status_2:
            case R.id.tv_status_3:
            case R.id.tv_status_4:
                Toast.makeText(getActivity(), "点击选择新状态修改，类似上面的企业选择（暂未实现）", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
