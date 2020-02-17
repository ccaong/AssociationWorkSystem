package com.example.gqsystem.ui.evaluate;

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

public class EvaluateActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"待开发", "编制中"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);
        toolbar.setTitle("评价评估");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fragments.add(EvaluateFragment.newInstance(1));
        fragments.add(EvaluateFragment.newInstance(2));


        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        tabLayout.setupWithViewPager(viewPager, false);
        FmPagerAdapter pagerAdapter = new FmPagerAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        for (int i = 0; i < titles.length; i++) {
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }

}
