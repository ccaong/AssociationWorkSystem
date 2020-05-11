package com.example.gqsystem.ui.update;

import android.view.View;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.FragmentUpdateBinding;

import androidx.lifecycle.ViewModelProvider;

/**
 * @author : devel
 * @date : 2020/3/27 10:44
 * @desc : 版本更新
 */
public class UpdateFragment extends BaseFragment<FragmentUpdateBinding, UpdateViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_update;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(UpdateViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }

    /**
     * 点击事件
     */
    public class EventListener {

        /**
         * 展示更新内容
         */
        public void showUpdateNote() {
            if (mDataBinding.recyclerViewPlan.getVisibility() == View.VISIBLE) {
                mDataBinding.recyclerViewPlan.setVisibility(View.GONE);
                mDataBinding.ivPlan.setImageResource(R.mipmap.ic_chevron_down);
            } else {
                mDataBinding.recyclerViewPlan.setVisibility(View.VISIBLE);
                mDataBinding.ivPlan.setImageResource(R.mipmap.ic_chevron_right_draft);
            }
        }
    }
}
