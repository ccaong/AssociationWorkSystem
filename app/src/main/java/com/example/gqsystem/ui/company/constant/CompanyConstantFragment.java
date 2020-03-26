package com.example.gqsystem.ui.company.constant;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.CompanyContactBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.util.CommonUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.company.content.CompanyContentFragment.PARAM1;

/**
 * @author devel
 * 企业联系人
 */
public class CompanyConstantFragment extends BaseFragment<FragmentListBinding, CompanyConstantViewModel> {

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
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(CompanyConstantViewModel.class);
        mViewModel.setId(id);
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
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> refresh.finishRefresh(1000));
        mDataBinding.refreshLayout.setOnLoadMoreListener(RefreshLayout::finishLoadMoreWithNoMoreData);
    }

    private void initRecyclerView() {
        CommonAdapter<CompanyContactBean> commonAdapter = new CommonAdapter<CompanyContactBean>(R.layout.company_item_constant, BR.companyConstant) {
            @Override
            public void addListener(View root, CompanyContactBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.iv_account_card).setOnClickListener(
                        v -> CommonUtils.addContact(itemData.getComUserName(), itemData.getComUserPhone(), itemData.getComUserEamil()));

                root.findViewById(R.id.tv_tel).setOnClickListener(v -> CommonUtils.callPhone(itemData.getComUserPhone()));
            }
        };
        mDataBinding.recyclerView.setAdapter(commonAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel.getPersonList().observe(this, personListBean -> commonAdapter.onItemDatasChanged(personListBean));
    }
}
