package com.example.gqsystem.ui.project.work.consulting;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.ProjectConsultInfo;
import com.example.gqsystem.bean.response.ProjectConsultOtherInfo;
import com.example.gqsystem.databinding.ProjectFragmentInfoConsultBinding;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.view.promp.PromptDialog;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_ID;

/**
 * @author : devel
 * @date : 2020/4/3 13:37
 * @desc :咨询类
 */
public class ProjectWorkConsultFragment extends BaseFragment<ProjectFragmentInfoConsultBinding, ProjectWorkConsultViewModel> {

    private String id;
    private CommonAdapter<ProjectConsultInfo> consultInfoCommonAdapter;
    private CommonAdapter<ProjectConsultOtherInfo> consultOtherInfoCommonAdapter;

    private PromptDialog promptDialog;

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        id = args.getString(PARAM_PRO_ID, "");
    }


    @Override
    protected boolean isSupportLoad() {
        return true;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.project_fragment_info_consult;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(ProjectWorkConsultViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        mViewModel.setId(id);
        mViewModel.loadData();

        initRecyclerView();
        promptDialog = new PromptDialog(MyActivityManager.getInstance().getCurrentActivity());
    }

    private void initRecyclerView() {
        consultInfoCommonAdapter = new CommonAdapter<ProjectConsultInfo>(R.layout.project_item_work_consult, BR.workConsult) {
            @Override
            public void addListener(View root, ProjectConsultInfo itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.tv_plan).setOnClickListener(v -> requestPermission(itemData.getWorkPlan()));
                root.findViewById(R.id.tv_summary).setOnClickListener(v -> requestPermission(itemData.getWorkSummary()));
            }
        };
        mDataBinding.rvScene.setAdapter(consultInfoCommonAdapter);
        mDataBinding.rvScene.setLayoutManager(new LinearLayoutManager(getContext()));


        consultOtherInfoCommonAdapter = new CommonAdapter<ProjectConsultOtherInfo>(R.layout.project_item_work_consult_other, BR.workConsultOther) {
            @Override
            public void addListener(View root, ProjectConsultOtherInfo itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.tv_otherChatAccessory).setOnClickListener(v -> requestPermission(itemData.getOtherChatAccessory()));
            }
        };
        mDataBinding.rvOther.setAdapter(consultOtherInfoCommonAdapter);
        mDataBinding.rvOther.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    protected void initDataChange() {
        mViewModel.getWorkInfo().observe(this, projectConsultInfo -> {
            consultInfoCommonAdapter.onItemDatasChanged(projectConsultInfo);
        });

        mViewModel.getWorkOtherInfo().observe(this, projectConsultInfo -> {
            consultOtherInfoCommonAdapter.onItemDatasChanged(projectConsultInfo);
        });

        mViewModel.getDownLoadState().observe(this, downLoadState -> {
            switch (downLoadState) {
                case ON_START:
                    promptDialog.showLoading("正在下载");
                    break;
                case DOWNLOADING:
                    break;
                case DOWNLOAD_FAIL:
                    promptDialog.showError("文件下载失败");
                    promptDialog.dismiss();
                    break;
                case DOWNLOAD_SUCCESS:
                    promptDialog.showInfo("文件下载完成");
                    promptDialog.dismiss();
                    break;
                default:
                    break;
            }
        });

    }
}
