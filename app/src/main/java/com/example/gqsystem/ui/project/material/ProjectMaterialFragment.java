package com.example.gqsystem.ui.project.material;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.databinding.FragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.Objects;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_ID;

/**
 * @author : devel
 * @date : 2020/4/2 11:22
 * @desc : 企业项目资料
 */
public class ProjectMaterialFragment extends BaseFragment<FragmentListBinding, ProjectMaterialViewModel> {

    private String id;
    private CommonAdapter<FileBean> fileAdapter;

    private int pos;
    private FileBean bean;

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
        mViewModel = new ViewModelProvider(this).get(ProjectMaterialViewModel.class);
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
        if (fileAdapter == null) {
            fileAdapter = new CommonAdapter<FileBean>(R.layout.project_item_material_layout, BR.fileCard) {
                @Override
                public void addListener(View root, FileBean itemData, int position) {
                    super.addListener(root, itemData, position);
                    root.findViewById(R.id.item).setOnClickListener(v -> {
                        pos = position;
                        bean = itemData;
                        requestPermission(itemData.getFileName());
                    });
                }
            };
        }
        mDataBinding.recyclerView.setAdapter(fileAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initDataChange() {

        mViewModel.getFileList().observe(this, fileBeans -> {
            //刷新完成，更新数据
            mDataBinding.refreshLayout.finishRefreshWithNoMoreData();
            if (fileAdapter != null) {
                fileAdapter.onItemDatasChanged(fileBeans);
            }
        });
        mViewModel.getDownLoadProgress().observe(this, progress -> {
            if (bean != null && fileAdapter != null) {
                bean.setDownloadProgress(progress);
                fileAdapter.notifyItemChanged(pos);
            }
        });

    }
}
