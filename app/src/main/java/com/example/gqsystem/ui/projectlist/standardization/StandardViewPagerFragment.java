package com.example.gqsystem.ui.projectlist.standardization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gqsystem.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author : devel
 * @date : 2020/3/12 13:42
 * @desc :
 */
public class StandardViewPagerFragment extends Fragment {

    private String[] titles = new String[]{"待开发", "咨询中", "待评审", "待整改", "待发证", "已结束"};

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        tabLayout = mRootView.findViewById(R.id.tab_layout);
        viewPager = mRootView.findViewById(R.id.view_pager);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();

    }


    private void initFragment() {

        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return StandardListFragment.newInstance(position + 1);
            }

            @Override
            public int getItemCount() {
                return titles.length;
            }
        });

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(titles[position]));
        tabLayoutMediator.attach();
    }
}
