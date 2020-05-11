package com.example.gqsystem.ui.project.contract;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.ProjectContractPaymentBean;
import com.example.gqsystem.databinding.ProjectFragmentContractBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_ID;

/**
 * @author : devel
 * @date : 2020/4/1 14:07
 * @desc : 合同信息
 */
public class ProjectContractFragment extends BaseFragment<ProjectFragmentContractBinding, ProjectContractViewModel> {

    private String id;
    private CommonAdapter<ProjectContractPaymentBean> commonAdapter;
    private CommonAdapter<FileBean> fileAdapter;
    private int pos;
    private FileBean bean;

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        id = args.getString(PARAM_PRO_ID, "");
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.project_fragment_contract;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(ProjectContractViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        mViewModel.setId(id);
        mViewModel.loadData();
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        commonAdapter = new CommonAdapter<>(R.layout.project_item_contract_payment, BR.contractPayment);
        mDataBinding.rvContract.setAdapter(commonAdapter);
        mDataBinding.rvContract.setLayoutManager(new LinearLayoutManager(getContext()));


        fileAdapter = new CommonAdapter<FileBean>(R.layout.project_item_content_file, BR.fileProject) {
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
        mDataBinding.rvContractFile.setAdapter(fileAdapter);
        mDataBinding.rvContractFile.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    /**
     * 更新数据
     */
    @Override
    protected void initDataChange() {
        mViewModel.getContractPaymentList().observe(this, list -> {
            //刷新完成，更新数据
            if (commonAdapter != null) {
                commonAdapter.onItemDatasChanged(list);
            }
        });


        mViewModel.getFileList().observe(this, list -> {
            if (fileAdapter != null) {
                fileAdapter.onItemDatasChanged(list);
            }
        });

        mViewModel.getDownLoadProgress().observe(this, progress -> {
            if (bean != null && fileAdapter != null) {
                bean.setDownloadProgress(progress);
                fileAdapter.notifyItemChanged(pos);
            }
        });
    }
}
