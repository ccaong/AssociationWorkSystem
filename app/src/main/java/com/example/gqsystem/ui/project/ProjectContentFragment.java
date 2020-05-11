package com.example.gqsystem.ui.project;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.ProjectListBean;
import com.example.gqsystem.common.CommonDialog;
import com.example.gqsystem.databinding.ProjectFragmentContentBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.ui.company.list.CompanyInvoiceDialog;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.MapUtil;
import com.example.gqsystem.util.ToastUtils;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author : devel
 * @date : 2020/4/1 14:06
 * @desc : 项目详情
 */
public class ProjectContentFragment extends BaseFragment<ProjectFragmentContentBinding, ProjectContentViewModel> {

    public final static String PARAM_PRO_ID = "param_pro_id";
    public final static String PARAM_PRO_CONTENT = "param_pro_content";
    public final static String PARAM_MINE_PRO = "param_mine_pro";
    private ProjectListBean.RecordsBean projectBean;
    private boolean mineProject;

    private CommonAdapter<FileBean> filePlanAdapter;
    private int pos;
    private FileBean bean;

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        projectBean = (ProjectListBean.RecordsBean) args.getSerializable(PARAM_PRO_CONTENT);
        mineProject = args.getBoolean(PARAM_MINE_PRO, false);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.project_fragment_content;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(ProjectContentViewModel.class);
        mViewModel.setProjectConstant(projectBean);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
        mDataBinding.setClick(new ProjectContentListener());
    }

    @Override
    protected void init() {
        initRecyclerView();
    }

    private void initRecyclerView() {

        if (filePlanAdapter == null) {
            filePlanAdapter = new CommonAdapter<FileBean>(R.layout.project_item_content_file, BR.fileProject) {
                @Override
                public void addListener(View root, FileBean itemData, int position) {
                    super.addListener(root, itemData, position);
                    root.findViewById(R.id.item).setOnClickListener(v -> {
                        bean = itemData;
                        pos = position;
                        requestPermission(itemData.getFileName());
                    });
                }
            };
        }
        mDataBinding.rvWorkProgram.setAdapter(filePlanAdapter);
        mDataBinding.rvWorkProgram.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    /**
     * 更新数据
     */
    @Override
    protected void initDataChange() {
        mViewModel.getFilePlan().observe(this, strings -> {
            if (filePlanAdapter != null) {
                filePlanAdapter.onItemDatasChanged(strings);
            }
        });

        mViewModel.getDownLoadProgress().observe(this, progress -> {
            if (bean != null && filePlanAdapter != null) {
                bean.setDownloadProgress(progress);
                filePlanAdapter.notifyItemChanged(pos);
            }
        });


        mViewModel.getCompanyPer().observe(this, bean -> {
            ProjectContractDialog dialog = ProjectContractDialog.newInstance(bean, projectBean.getComName());
            dialog.show(getChildFragmentManager(), "person");
        });
    }


    /**
     * 点击事件
     */
    public class ProjectContentListener {

        /**
         * 项目详细信息
         */
//        public void btnProjectInfo() {
//            Bundle bundle = new Bundle();
//            bundle.putString(PARAM_PRO_ID, projectBean.getId());
//            if (CommonUtils.isStringEmpty(projectBean.getProAttribute_dictText())) {
//                ToastUtils.showToast("暂无详情信息！");
//                return;
//            }
//            switch (projectBean.getProAttribute_dictText()) {
//                case "咨询类项目":
//                    NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_work_consult, bundle);
//                    break;
//                case "评审类项目":
//                    NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_work_judge, bundle);
//                    break;
//                case "评估评价报告及应急预案":
//                    NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_work_assess, bundle);
//                    break;
//                case "政府项目":
//                    NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_work_gov, bundle);
//                    break;
//                default:
//                    break;
//            }
//        }

        /**
         * 咨询类项目
         */
        public void btnProjectConsultDetail() {
            Bundle bundle = new Bundle();
            bundle.putString(PARAM_PRO_ID, projectBean.getId());
            NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_work_consult, bundle);
        }

        /**
         * 评审类项目
         */
        public void btnProjectJudgeDetail() {
            Bundle bundle = new Bundle();
            bundle.putString(PARAM_PRO_ID, projectBean.getId());
            NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_work_judge, bundle);
        }

        /**
         * 评估评价报告及应急预案信息
         */
        public void btnProjectAssessDetail() {
            Bundle bundle = new Bundle();
            bundle.putString(PARAM_PRO_ID, projectBean.getId());
            NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_work_assess, bundle);
        }

        /**
         * 政府类项目信息
         */
        public void btnProjectGovDetail() {
            Bundle bundle = new Bundle();
            bundle.putString(PARAM_PRO_ID, projectBean.getId());
            NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_work_gov, bundle);
        }

        /**
         * 合同信息
         */
        public void btnContract() {
            if (mineProject) {
                Bundle bundle = new Bundle();
                bundle.putString(PARAM_PRO_ID, projectBean.getId());
                NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_contract, bundle);
            }
        }

        /**
         * 成本核算
         */
        public void btnCostAccounting() {
            if (mineProject) {
                Bundle bundle = new Bundle();
                bundle.putString(PARAM_PRO_ID, projectBean.getId());
                NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_cost_account, bundle);
            }
        }

        /**
         * 企业资料
         */
        public void btnComMaterial() {
            Bundle bundle = new Bundle();
            bundle.putString(PARAM_PRO_ID, projectBean.getId());
            NavHostFragment.findNavController(ProjectContentFragment.this).navigate(R.id.project_material, bundle);
        }
    }
}
