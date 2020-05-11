package com.example.gqsystem.ui.company.content;

import android.os.Bundle;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.common.CommonDialog;
import com.example.gqsystem.databinding.CompanyFragmentContentBinding;
import com.example.gqsystem.ui.company.list.CompanyInvoiceDialog;
import com.example.gqsystem.util.MapUtil;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import static com.example.gqsystem.ui.project.list.ProjectListFragment.COMPANY_ID;

/**
 * @author devel
 * 企业详情
 */
public class CompanyContentFragment extends BaseFragment<CompanyFragmentContentBinding, CompanyContentViewModel> {

    public static final String PARAM1 = "param_1";

    private String id;

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        id = args.getString(PARAM1, "");
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
         * 跳转到导航
         *
         */
        public void btnNavigation(String company,String address) {
            CommonDialog dialog = CommonDialog.newInstance("导航", "是否打开手机地图，导航到" + company);
            dialog.show(getChildFragmentManager(), "navigation");
            dialog.setConfirmClickListener(() -> MapUtil.startNavigation(address));
        }

        /**
         * 联系人信息
         */
        public void btnConstant() {
            Bundle bundle = new Bundle();
            bundle.putString(PARAM1, id);
            NavHostFragment.findNavController(CompanyContentFragment.this).navigate(R.id.company_constant, bundle);
        }

        /**
         * 项目信息
         */
        public void btnProject() {
            Bundle bundle = new Bundle();
            bundle.putString(COMPANY_ID, id);
            NavHostFragment.findNavController(CompanyContentFragment.this).navigate(R.id.project_list, bundle);
        }

        /**
         * 关联企业信息
         */
        public void btnRelated() {
            Bundle bundle = new Bundle();
            bundle.putString(PARAM1, id);
            NavHostFragment.findNavController(CompanyContentFragment.this).navigate(R.id.company_related, bundle);
        }
    }
}
