package com.example.gqsystem.ui.main.review;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.bean.response.ReviewerListBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author devel
 * 评审专家信息
 */
public class ReviewerFragment extends BaseFragment<FragmentListBinding, ReviewerViewModel> {

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
        mViewModel = new ViewModelProvider(getActivity()).get(ReviewerViewModel.class);
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
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refresh -> mViewModel.refreshData(false));
    }


    private void initRecyclerView() {

        CommonAdapter<ReviewerListBean.RecordsBean> commonAdapter = new CommonAdapter<ReviewerListBean.RecordsBean>(R.layout.reviewer_item_info, BR.expertBean) {
            @Override
            public void addListener(View root, ReviewerListBean.RecordsBean itemData, int position) {
                super.addListener(root, itemData, position);

                root.findViewById(R.id.item).setOnClickListener(v -> {
                    mViewModel.setReviewer(itemData);
                    NavHostFragment.findNavController(ReviewerFragment.this).navigate(R.id.nav_reviewer_information_detail);
                });

                root.findViewById(R.id.tv_tel).setOnClickListener(v -> {
                    //拨打电话
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + itemData.getPhone());
                    intent.setData(data);
                    startActivity(intent);
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
