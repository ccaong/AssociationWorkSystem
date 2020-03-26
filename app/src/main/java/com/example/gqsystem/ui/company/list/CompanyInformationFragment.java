package com.example.gqsystem.ui.company.list;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.CompanyListBean;
import com.example.gqsystem.common.CommonDialog;
import com.example.gqsystem.databinding.CompanyFragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.util.MapUtil;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.Objects;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.company.content.CompanyContentFragment.PARAM1;

/**
 * @author devel
 * 企业基本信息列表
 */
public class CompanyInformationFragment extends BaseFragment<CompanyFragmentListBinding, CompanyInformationViewModel> {

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.company_fragment_list;
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
        initRecyclerView();
    }

    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setPrimaryColorsId(android.R.color.white, R.color.colorPrimary);
        mDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(getContext())));
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refresh -> mViewModel.refreshData(false));
    }


    private void initRecyclerView() {
        CommonAdapter<CompanyListBean.RecordsBean> commonAdapter = new CommonAdapter<CompanyListBean.RecordsBean>(R.layout.company_item_information, BR.companyBean) {
            @Override
            public void addListener(View root, CompanyListBean.RecordsBean itemData, int position) {
                super.addListener(root, itemData, position);

                root.findViewById(R.id.card_view).setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString(PARAM1, itemData.getId());
                    NavHostFragment.findNavController(CompanyInformationFragment.this).navigate(R.id.company_content, bundle);
                });

                root.findViewById(R.id.iv_map_navigation).setOnClickListener(v ->
                        showNavigationDialog(1, 1, itemData.getComAddressDetail(), itemData.getComName()));
                root.findViewById(R.id.tv_invoice_information).setOnClickListener(v -> mViewModel.loadCompanyList(itemData.getId()));

            }
        };

        mDataBinding.recyclerView.setAdapter(commonAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getCompanyList().observe(this, companyList -> {
            if (companyList.getCurrent() >= companyList.getPages()) {
                mDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
            mDataBinding.refreshLayout.finishRefresh();
            mDataBinding.refreshLayout.finishLoadMore();
            commonAdapter.onItemDatasChanged(companyList.getRecords());
        });
    }

    @Override
    protected void initDataChange() {
        mViewModel.getInvoiceInfoBean().observe(this, invoiceInfoBean -> {
            CompanyInvoiceDialog dialog = CompanyInvoiceDialog.newInstance(invoiceInfoBean);
            dialog.show(getChildFragmentManager(), "invoice");
        });
    }


    /**
     * 跳转到导航
     *
     * @param dlat  终点纬度
     * @param dlon  终点经度
     * @param dname 终点名称
     */
    private void showNavigationDialog(double dlat, double dlon, String dname, String company) {
        CommonDialog dialog = CommonDialog.newInstance("导航", "是否打开手机地图，导航到" + company);
        dialog.show(getChildFragmentManager(), "navigation");
        dialog.setConfirmClickListener(() -> MapUtil.startNavigation(dlat, dlon, dname));
    }
}
