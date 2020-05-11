package com.example.gqsystem.ui.project.viewpager;

import android.os.Bundle;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.FragmentViewpagerBinding;
import com.example.gqsystem.ui.project.list.ProjectListFragment;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import static com.example.gqsystem.ui.project.list.ProjectListFragment.PARAM_PRO_TYPE;

/**
 * @author : devel
 * @date : 2020/4/2 16:06
 * @desc :
 */
public class ProjectViewPagerFragment extends BaseFragment<FragmentViewpagerBinding, ProjectViewPagerViewModel> {

    private String mProType;

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        mProType = args.getString(PARAM_PRO_TYPE, "");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_viewpager;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(ProjectViewPagerViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        mViewModel.setType(mProType);
    }

    @Override
    protected void initDataChange() {
        mViewModel.getTitles().observe(this, strings -> {
            mDataBinding.viewPager.setAdapter(new FragmentStateAdapter(ProjectViewPagerFragment.this) {
                @NonNull
                @Override
                public Fragment createFragment(int position) {
                    return getFragment(strings[position]);
                }

                @Override
                public int getItemCount() {
                    return strings.length;
                }
            });
            TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mDataBinding.tabLayout, mDataBinding.viewPager, (tab, position) -> tab.setText(strings[position]));
            tabLayoutMediator.attach();
        });
    }

    private Fragment getFragment(String statusStr) {
        switch (statusStr) {
            case "待开发":
                return ProjectListFragment.newInstance(mProType, "1");
            case "咨询中":
                return ProjectListFragment.newInstance(mProType, "2");
            case "待评审":
                return ProjectListFragment.newInstance(mProType, "3");
            case "待整改":
                return ProjectListFragment.newInstance(mProType, "4");
            case "待发证":
                return ProjectListFragment.newInstance(mProType, "5");
            case "已结束":
                return ProjectListFragment.newInstance(mProType, "6");
            case "编制中":
                return ProjectListFragment.newInstance(mProType, "7");
            case "洽谈中":
                return ProjectListFragment.newInstance(mProType, "8");
            case "运维中":
                return ProjectListFragment.newInstance(mProType, "9");
            default:
                return ProjectListFragment.newInstance(mProType, "");
        }
    }
}
