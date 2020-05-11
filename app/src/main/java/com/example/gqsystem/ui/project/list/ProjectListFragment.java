package com.example.gqsystem.ui.project.list;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.ProjectListBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.util.CommonUtils;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.company.content.CompanyContentFragment.PARAM1;
import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_MINE_PRO;
import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_CONTENT;

/**
 * @author : devel
 * @date : 2020/4/1 14:06
 * @desc : 项目列表
 */
public class ProjectListFragment extends BaseFragment<FragmentListBinding, ProjectListViewModel> {

    public final static String PARAM_PRO_TYPE = "param_pro_type";
    public final static String PARAM_PRO_STATUS = "param_pro_status";
    public final static String COMPANY_ID = "param_company_id";

    private CommonAdapter<ProjectListBean.RecordsBean> commonAdapter;

    private String mProType;
    private String mProStatus;
    private String mCompanyId;

    public static ProjectListFragment newInstance(String type, String status) {
        ProjectListFragment projectListFragment = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_PRO_TYPE, type);
        bundle.putString(PARAM_PRO_STATUS, status);
        projectListFragment.setArguments(bundle);
        return projectListFragment;
    }

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        mProType = args.getString(PARAM_PRO_TYPE, "");
        mProStatus = args.getString(PARAM_PRO_STATUS, "");
        mCompanyId = args.getString(COMPANY_ID, "");
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
        mViewModel = new ViewModelProvider(this).get(ProjectListViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        mViewModel.setProType(mProType, mProStatus,mCompanyId);

        setTitle();
        mViewModel.loadData();
        initRefreshLayout();
        initRecyclerView();
    }

    private void setTitle() {
        Toolbar toolbar = MyActivityManager.getInstance().getCurrentActivity().findViewById(R.id.toolbar);
        if (toolbar != null && !CommonUtils.isStringEmpty(mProType)) {
            toolbar.setTitle(mProType);
        }
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


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        commonAdapter = new CommonAdapter<ProjectListBean.RecordsBean>(R.layout.search_item_project_layout, com.example.gqsystem.BR.project) {
            @Override
            public void addListener(View root, ProjectListBean.RecordsBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.item).setOnClickListener(v -> {
                    //项目详情
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(PARAM_PRO_CONTENT, itemData);
                    bundle.putBoolean(PARAM_MINE_PRO, true);
                    NavHostFragment.findNavController(ProjectListFragment.this).navigate(R.id.project_content, bundle);
                });

                root.findViewById(R.id.tv_company_name).setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString(PARAM1, itemData.getComId());
                    NavHostFragment.findNavController(ProjectListFragment.this).navigate(R.id.company_content, bundle);
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(commonAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 更新数据
     */
    @Override
    protected void initDataChange() {
        mViewModel.getProjectList().observe(this, projectListBean -> {
            //刷新完成，更新数据
            if (projectListBean.getCurrent() >= projectListBean.getPages()) {
                mDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
            mDataBinding.refreshLayout.finishRefresh();
            mDataBinding.refreshLayout.finishLoadMore();
            if (commonAdapter != null) {
                commonAdapter.onItemDatasChanged(projectListBean.getRecords());
            }
        });
    }
}
