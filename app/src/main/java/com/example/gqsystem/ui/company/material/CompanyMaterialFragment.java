package com.example.gqsystem.ui.company.material;

import android.os.Bundle;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import androidx.lifecycle.ViewModelProvider;

import static com.example.gqsystem.ui.company.content.CompanyContentFragment.PARAM1;

/**
 * @author : devel
 * @date : 2020/3/31 13:06
 * @desc :企业资料
 */
public class CompanyMaterialFragment extends BaseFragment<FragmentListBinding, CompanyMaterialViewModel> {

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
        mViewModel = new ViewModelProvider(this).get(CompanyMaterialViewModel.class);
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
//        CommonAdapter<CompanyRelatedBean> commonAdapter = new CommonAdapter<CompanyRelatedBean>(R.layout.company_item_related, com.example.gqsystem.BR.companyRelated) {
//            @Override
//            public void addListener(View root, CompanyRelatedBean itemData, int position) {
//                super.addListener(root, itemData, position);
//                root.findViewById(R.id.item).setOnClickListener(
//                        v -> {
//                            Bundle bundle = new Bundle();
//                            bundle.putString(PARAM1, itemData.getId());
//                            NavHostFragment.findNavController(CompanyRelatedFragment.this).navigate(R.id.company_content, bundle);
//                        });
//            }
//        };
//        mDataBinding.recyclerView.setAdapter(commonAdapter);
//        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mViewModel.getRelationCompany().observe(this, commonAdapter::onItemDatasChanged);
    }
}
