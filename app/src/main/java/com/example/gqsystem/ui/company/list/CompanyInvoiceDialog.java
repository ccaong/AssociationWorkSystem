package com.example.gqsystem.ui.company.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.gqsystem.R;
import com.example.gqsystem.bean.response.InvoiceInfoBean;
import com.example.gqsystem.databinding.DialogInvoiceInformationBinding;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.ToastUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

/**
 * @author : devel
 * @date : 2020/3/6 10:30
 * @desc : 企业开票信息Dialog
 */
public class CompanyInvoiceDialog extends DialogFragment {
    private static final String PARAM_INFO_KEY = "invoice_info";

    private DialogInvoiceInformationBinding mDataBinding;

    public static CompanyInvoiceDialog newInstance(InvoiceInfoBean data) {
        CompanyInvoiceDialog fragment = new CompanyInvoiceDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PARAM_INFO_KEY, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_invoice_information, container, false);
        mDataBinding.setLifecycleOwner(this);
        return mDataBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            InvoiceInfoBean info = (InvoiceInfoBean) getArguments().getSerializable(PARAM_INFO_KEY);
            mDataBinding.setData(info);
        }
        mDataBinding.setInvoiceDialog(this);
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
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
