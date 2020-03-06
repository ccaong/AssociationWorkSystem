package com.example.gqsystem.ui.company.list;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.InvoiceInfoBean;
import com.example.gqsystem.common.CommonDialog;
import com.example.gqsystem.databinding.CompanyFragmentInformationBinding;
import com.example.gqsystem.util.MapUtil;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import androidx.lifecycle.ViewModelProviders;

/**
 * @author devel
 * 企业基本信息
 */
public class CompanyInformationFragment extends BaseFragment<CompanyFragmentInformationBinding, CompanyInformationViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.company_fragment_information;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(CompanyInformationViewModel.class);
    }

    @Override
    protected void bindViewModel() {
    }

    @Override
    protected void init() {
        mViewModel.loadData();
        initRefreshLayout();
    }

    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setPrimaryColorsId(android.R.color.white, R.color.colorPrimary);
        mDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        mDataBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mViewModel.refreshData(true);
                refreshlayout.finishRefresh(2000);
                showInvoiceDialog();
            }
        });
        mDataBinding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                mViewModel.refreshData(false);
                refreshlayout.finishLoadMore(2000);
            }
        });
    }

    /**
     * 跳转到导航
     *
     * @param dlat  终点纬度
     * @param dlon  终点经度
     * @param dname 终点名称
     */
    private void showNavigationDialog(double dlat, double dlon, String dname) {
        CommonDialog dialog = CommonDialog.newInstance("导航", "是否打开手机地图，导航到" + "XXXXXXX公司");
        dialog.show(getChildFragmentManager(), "navigation");

        dialog.setConfirmClickListener(new CommonDialog.ConfirmListener() {
            @Override
            public void onClickComplete() {
                MapUtil.startNavigation(dlat, dlon, dname);
            }
        });
    }

    /**
     * 展示开票信息
     */
    private void showInvoiceDialog() {
        InvoiceInfoBean bean = new InvoiceInfoBean("XXXXXX公司", "type?", "123456", "bank", "1233211232123", "12345678900");
        CompanyInvoiceDialog dialog = CompanyInvoiceDialog.newInstance(bean);
        dialog.show(getChildFragmentManager(), "invoice");
    }

}
