package com.example.gqsystem.ui.home;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.HomeFragmentBinding;
import com.example.gqsystem.ui.adapter.ViewPagerAdapter;
import com.example.gqsystem.ui.home.homepage.HomePageFragment;
import com.example.gqsystem.ui.metting.list.MeetingFragment;
import com.example.gqsystem.ui.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author devel
 */
public class HomeFragment extends BaseFragment<HomeFragmentBinding, HomeViewModel> {


    private String[] titles = new String[]{"首页", "查询", "会议"};

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        List<Fragment> fragments = new ArrayList<>();
        if (fragments.size() == 0) {
            fragments.add(new HomePageFragment());
            fragments.add(new SearchFragment());
            fragments.add(new MeetingFragment());
        }
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPage);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, titles);
        mDataBinding.viewPage.setAdapter(adapter);
    }
}
