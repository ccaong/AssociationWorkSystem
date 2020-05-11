package com.example.gqsystem.ui.company.list;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.CompanyListBean;
import com.example.gqsystem.common.CommonDialog;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.util.MapUtil;
import com.example.gqsystem.util.ScreenUtil;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.Objects;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.company.content.CompanyContentFragment.PARAM1;

/**
 * @author devel
 * 企业基本信息列表
 */
public class CompanyListFragment extends BaseFragment<FragmentListBinding, CompanyListViewModel> {

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(CompanyListViewModel.class);
    }

    @Override
    protected void bindViewModel() {
    }

    @Override
    protected void init() {
        setHasOptionsMenu(true);
        mViewModel.loadData();
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        //通过MenuItem得到SearchView
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.setSearchText(newText);
                return false;
            }
        });
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
                    NavHostFragment.findNavController(CompanyListFragment.this).navigate(R.id.company_content, bundle);
                });

                root.findViewById(R.id.iv_map_navigation).setOnClickListener(v ->
                        showNavigationDialog(itemData.getComAddressDetail(), itemData.getComName()));
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
     * @param dname 终点名称
     */
    private void showNavigationDialog(String dname, String company) {
        ScreenUtil.resetScreen(getActivity());
        CommonDialog dialog = CommonDialog.newInstance("导航", "是否打开手机地图，导航到" + company);
        dialog.show(getChildFragmentManager(), "navigation");
        dialog.setConfirmClickListener(() -> MapUtil.startNavigation(dname));
    }
}
