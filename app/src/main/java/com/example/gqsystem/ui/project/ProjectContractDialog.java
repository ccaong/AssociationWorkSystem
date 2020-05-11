package com.example.gqsystem.ui.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.gqsystem.R;
import com.example.gqsystem.bean.response.CompanyPerInfoBean;
import com.example.gqsystem.databinding.ProjectDialogContractInfoBinding;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.ToastUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

/**
 * @author : devel
 * @date : 2020/3/6 10:30
 * @desc : 企业对接联系人
 */
public class ProjectContractDialog extends DialogFragment {
    private static final String PARAM_INFO_KEY = "person_info";
    private static final String PARAM_COMPANY_INFO = "company_name";

    private ProjectDialogContractInfoBinding mDataBinding;

    public static ProjectContractDialog newInstance(CompanyPerInfoBean data, String companyName) {
        ProjectContractDialog fragment = new ProjectContractDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PARAM_INFO_KEY, data);
        bundle.putString(PARAM_COMPANY_INFO, companyName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.project_dialog_contract_info, container, false);
        mDataBinding.setLifecycleOwner(this);
        return mDataBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            CompanyPerInfoBean info = (CompanyPerInfoBean) getArguments().getSerializable(PARAM_INFO_KEY);
            mDataBinding.setData(info);
            mDataBinding.setComName(getArguments().getString(PARAM_COMPANY_INFO));
        }
        mDataBinding.setProjectContractDialog(this);
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
    }


    public void call(String tel) {
        CommonUtils.callPhone(tel);
    }

    /**
     * 复制信息到系统粘贴板
     *
     * @param info
     */
    public void copyInvoiceInfo(String info) {
        CommonUtils.setClipoard(info);
        ToastUtils.showToast("信息已经复制到系统粘贴板");
    }
}
