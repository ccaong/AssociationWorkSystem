package com.example.gqsystem.ui.project.work.assess;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.ProjectAssessInfoBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.Objects;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_ID;

/**
 * @author : devel
 * @date : 2020/4/7 9:15
 * @desc :评估评价
 */
public class ProjectWorkAssessFragment extends BaseFragment<FragmentListBinding, ProjectWorkAssessViewModel> {

    private String id;
    private CommonAdapter<ProjectAssessInfoBean> fileAdapter;

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        id = args.getString(PARAM_PRO_ID, "");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(ProjectWorkAssessViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        mViewModel.setId(id);
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
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> mViewModel.refreshData());
        mDataBinding.refreshLayout.setOnLoadMoreListener(refresh -> refresh.finishLoadMore(1000));
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        fileAdapter = new CommonAdapter<ProjectAssessInfoBean>(R.layout.project_item_assess_layout, BR.assessProjectInfo) {
            @Override
            public void addListener(View root, ProjectAssessInfoBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.tv_file).setOnClickListener(v -> requestPermission(itemData.getAccessory()));
                root.findViewById(R.id.tv_picture).setOnClickListener(v -> requestPermission(itemData.getPicture()));
            }
        };
        mDataBinding.recyclerView.setAdapter(fileAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initDataChange() {
        mViewModel.getWorkInfo().observe(this, fileBeans -> {
            //刷新完成，更新数据
            mDataBinding.refreshLayout.finishRefreshWithNoMoreData();
            if (fileAdapter != null) {
                fileAdapter.onItemDatasChanged(fileBeans);
            }
        });
    }
}
