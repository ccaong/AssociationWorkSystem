package com.example.gqsystem.ui.company.content;

import android.os.Bundle;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.CompanyFragmentContentBinding;
import com.example.gqsystem.ui.company.list.CompanyInvoiceDialog;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

/**
 * @author devel
 * 企业详情
 */
public class CompanyContentFragment extends BaseFragment<CompanyFragmentContentBinding, CompanyContentViewModel> {
    public static final String PARAM1 = "param_1";

    private Integer id;

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        id = args.getInt(PARAM1, -1);
    }

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.company_fragment_content;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(CompanyContentViewModel.class);
        mViewModel.setId(id);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
        mDataBinding.setClickListener(new EventListener());
    }

    @Override
    protected void init() {
        mViewModel.loadCompanyInfo();
        mViewModel.loadCompanyIndustry();
    }

    @Override
    protected void initDataChange() {
        mViewModel.getInvoiceInfoBean().observe(this, invoiceInfoBean -> {
            CompanyInvoiceDialog dialog = CompanyInvoiceDialog.newInstance(invoiceInfoBean);
            dialog.show(getChildFragmentManager(), "invoice");
        });
    }


    /**
     * 点击事件
     */
    public class EventListener {

        /**
         * 联系人信息
         */
        public void btnConstant() {
            Bundle bundle = new Bundle();
            bundle.putInt(PARAM1, id);
            NavHostFragment.findNavController(CompanyContentFragment.this).navigate(R.id.company_constant, bundle);
        }
    }
}
