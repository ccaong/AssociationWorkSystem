package com.example.gqsystem.ui.main.person;

import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.PersonListBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.util.CommonUtils;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author devel
 * 评审专家信息
 */
public class PersonFragment extends BaseFragment<FragmentListBinding, PersonViewModel> {

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
        mViewModel = new ViewModelProvider(this).get(PersonViewModel.class);
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
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refreshlayout -> mViewModel.refreshData(false));
    }


    private void initRecyclerView() {

        CommonAdapter<PersonListBean.RecordsBean> commonAdapter = new CommonAdapter<PersonListBean.RecordsBean>(R.layout.person_item_info, BR.personBean) {
            @Override
            public void addListener(View root, PersonListBean.RecordsBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.tv_tel).setOnClickListener(v -> {
                    //拨打电话
                    CommonUtils.callPhone(itemData.getPhone());
                });
            }
        };

        mDataBinding.recyclerView.setAdapter(commonAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewModel.getPersonList().observe(this, personListBean -> {
            if (personListBean.getCurrent() >= personListBean.getPages()) {
                mDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
            mDataBinding.refreshLayout.finishRefresh();
            mDataBinding.refreshLayout.finishLoadMore();
            commonAdapter.onItemDatasChanged(personListBean.getRecords());
        });
    }
}
