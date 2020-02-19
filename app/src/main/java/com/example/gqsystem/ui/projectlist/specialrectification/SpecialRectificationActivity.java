package com.example.gqsystem.ui.projectlist.specialrectification;

import android.os.Bundle;
import android.view.View;

import com.example.gqsystem.R;
import com.example.gqsystem.ui.adapter.FmPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author devel
 */
public class SpecialRectificationActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"涉氨制冷","粉尘防爆","有限空间","金属冶炼"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);

        toolbar.setTitle("专项整治");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fragments.add(SpecialRectificationFragment.newInstance(1));
        fragments.add(SpecialRectificationFragment.newInstance(2));
        fragments.add(SpecialRectificationFragment.newInstance(3));
        fragments.add(SpecialRectificationFragment.newInstance(4));

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
