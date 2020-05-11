package com.example.gqsystem;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.bean.response.UserDataBean;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.databinding.ActivityMainBinding;
import com.example.gqsystem.ui.activity.login.LoginActivity;
import com.example.gqsystem.ui.mine.MineViewModel;
import com.example.gqsystem.util.ActivitySkipUtil;
import com.example.gqsystem.view.CircleImageView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.orhanobut.hawk.Hawk;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * @author devel
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MineViewModel> {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_company)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        BottomNavigationView navView = findViewById(R.id.nav_view_bottom);
        NavigationUI.setupWithNavController(navView, navController);

        getLogoutStatus();
    }

    /**
     * 获取退出状态
     */
    private void getLogoutStatus() {
        mViewModel.getLogoutStatus().observe(this, integer -> {
            if (integer == -1) {
                ActivitySkipUtil.skipActivity(MainActivity.this, LoginActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUserData();
    }

    /**
     * 初始化侧边栏用户信息
     */
    private void initUserData() {
        UserDataBean userDataBean = Hawk.get(Constants.HawkCode.LOGIN_DATA);
        mViewModel.setUserData(userDataBean);

        TextView tvName = mDataBinding.navView.getHeaderView(0).findViewById(R.id.tv_name);
        CircleImageView ivHeader = mDataBinding.navView.getHeaderView(0).findViewById(R.id.iv_header);
        tvName.setText(userDataBean.getUserInfo().getRealname());
        if (userDataBean.getUserInfo().getSex() == 1) {
            ivHeader.setImageResource(R.mipmap.header_male);
        } else {
            ivHeader.setImageResource(R.mipmap.header_female);
        }

        //点击头部个人信息
        mDataBinding.navView.getHeaderView(0).setOnClickListener(v -> {
            //跳转到mine界面
            Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.mine_fragment);
            drawer.closeDrawer(GravityCompat.START);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("记录操作", "Real MainActivity onDestroy");
    }

    @Override
    public void onBackPressed() {
        if (drawer == null) {
            super.onBackPressed();
        }
        // 返回键: 侧滑开着就将其关闭, 关着则退出应用
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishApp();
        }
    }

    /**
     * 记录用户首次点击返回键的时间
     */
    private long firstTime = 0;

    /**
     * 退出应用
     */
    private void finishApp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        if (navController.getBackStack().size() == 2) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
            } else {
                finish();
            }
        } else {
            super.onBackPressed();
        }
    }
}
