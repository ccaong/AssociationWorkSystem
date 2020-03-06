package com.example.gqsystem.ui.adapter;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author : devel
 * @date : 2020/2/28 13:32
 * @desc :
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {


    private Fragment mCurrentFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> sTitle = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments, List<String> list) {
        super(fm);
        fragmentList = fragments;
        sTitle = list;
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        fragmentList = fragments;
        sTitle = Arrays.asList(titles);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return sTitle.get(position);
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }


    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

}
