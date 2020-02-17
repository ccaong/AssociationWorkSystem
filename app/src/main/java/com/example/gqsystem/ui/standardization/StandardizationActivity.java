package com.example.gqsystem.ui.standardization;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.gqsystem.R;
import com.example.gqsystem.ui.adapter.FmPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StandardizationActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    List<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"待开发","咨询中","待评审","待整改","待发证","已结束"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);

        toolbar.setTitle("安全生产标准化");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        fragments.add(StandardizationStatus1Fragment.newInstance(1));
        fragments.add(StandardizationStatus1Fragment.newInstance(2));
        fragments.add(StandardizationStatus1Fragment.newInstance(3));
        fragments.add(StandardizationStatus1Fragment.newInstance(4));
        fragments.add(StandardizationStatus1Fragment.newInstance(5));
        fragments.add(StandardizationStatus1Fragment.newInstance(6));


        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab());
        }

        tabLayout.setupWithViewPager(viewPager,false);
        FmPagerAdapter  pagerAdapter = new FmPagerAdapter(fragments,getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        for(int i=0;i<titles.length;i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }
}
