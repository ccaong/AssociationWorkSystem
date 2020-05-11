package com.example.gqsystem.ui.main.leaderstroke;

import android.os.Bundle;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.databinding.LeaderFragmentEditBinding;
import com.example.gqsystem.util.ToastUtils;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

/**
 * @author : devel
 * @date : 2020/4/14 13:50
 * @desc :
 */
public class LeaderActivityEditFragment extends BaseFragment<LeaderFragmentEditBinding, LeaderActivityEditViewModel> {

    final static String PARAM_LEADER_ACTIVITY = "leader_activity";

    private LeaderActivityListBean.RecordsBean bean;

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        bean = (LeaderActivityListBean.RecordsBean) args.getSerializable(PARAM_LEADER_ACTIVITY);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.leader_fragment_edit;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(LeaderActivityEditViewModel.class);
        if (bean != null) {
            mViewModel.editActity(bean);
        }
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initDataChange() {
        super.initDataChange();
        mViewModel.getAddSuccess().observe(this, integer -> {
            if (integer == 1) {
                ToastUtils.showToast("操作成功！");
                NavHostFragment.findNavController(LeaderActivityEditFragment.this).navigateUp();
            }
            if (integer == -1) {
                ToastUtils.showToast("操作失败，请重试！");
            }
        });
    }
}
