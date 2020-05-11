package com.example.gqsystem.ui.project.work.gov;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.ProjectGovInfo;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.enums.DownLoadState;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.view.promp.PromptDialog;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.Objects;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_ID;

/**
 * @author : devel
 * @date : 2020/4/3 11:38
 * @desc : 政府项目工作过程详情
 */
public class ProjectWorkGovFragment extends BaseFragment<FragmentListBinding, ProjectWorkGovViewModel> {

    private String id;
    private CommonAdapter<ProjectGovInfo> fileAdapter;

    private PromptDialog promptDialog;


    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        id = args.getString(PARAM_PRO_ID, "");
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
        mViewModel = new ViewModelProvider(this).get(ProjectWorkGovViewModel.class);
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
        promptDialog = new PromptDialog(MyActivityManager.getInstance().getCurrentActivity());
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
        fileAdapter = new CommonAdapter<ProjectGovInfo>(R.layout.project_item_info_gov, BR.govProjectInfo) {
            @Override
            public void addListener(View root, ProjectGovInfo itemData, int position) {
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

        mViewModel.getDownLoadState().observe(this, downLoadState -> {
            switch (downLoadState) {
                case ON_START:
                    promptDialog.showLoading("正在下载");
                    break;
                case DOWNLOADING:
                    break;
                case DOWNLOAD_FAIL:
                    promptDialog.showError("文件下载失败");
                    promptDialog.dismiss();
                    break;
                case DOWNLOAD_SUCCESS:
                    promptDialog.showInfo("文件下载完成");
                    promptDialog.dismiss();
                    break;
                default:
                    break;
            }
        });
    }
}
