package com.example.gqsystem.ui.project.cost;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.databinding.ProjectFragmentCostAccountBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_ID;

/**
 * @author : devel
 * @date : 2020/4/2 9:13
 * @desc : 成本核算
 */
public class ProjectCostAccountFragment extends BaseFragment<ProjectFragmentCostAccountBinding, ProjectCostAccountViewModel> {

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
        return R.layout.project_fragment_cost_account;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(ProjectCostAccountViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);

    }

    @Override
    protected void init() {
        mViewModel.loadData(id);
        initRecyclerView();
    }

    private void initRecyclerView() {
        fileAdapter = new CommonAdapter<FileBean>(R.layout.meeting_item_file, com.example.gqsystem.BR.file) {
            @Override
            public void addListener(View root, FileBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.item).setOnClickListener(v -> {
                    bean = itemData;
                    pos = position;
                    requestPermission(itemData.getFileName());
                });
            }
        };
        mDataBinding.rvFile.setAdapter(fileAdapter);
        mDataBinding.rvFile.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 更新数据
     */
    @Override
    protected void initDataChange() {
        mViewModel.getCostBean().observe(this, bean -> {
            int total = bean.getCarFare() + bean.getHotelExpense() + bean.getSubsidy() + bean.getExpertFee() + bean.getExpertMealFee() + bean.getPrintingFee() + bean.getRestsFee();

            List<Float> list = new ArrayList<>();
            list.add((float) bean.getCarFare() / total);
            list.add((float) bean.getHotelExpense() / total);
            list.add((float) bean.getSubsidy() / total);
            list.add((float) bean.getExpertFee() / total);
            list.add((float) bean.getExpertMealFee() / total);
            list.add((float) bean.getPrintingFee() / total);
            list.add((float) bean.getRestsFee() / total);

            mDataBinding.chartView.updateChart(list);
        });


        mViewModel.getCostFile().observe(this, bean -> {
            if (fileAdapter != null) {
                fileAdapter.onItemDatasChanged(bean);
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
