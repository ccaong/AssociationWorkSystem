package com.example.gqsystem.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.SearchHistoryBean;
import com.example.gqsystem.bean.response.ProjectListBean;
import com.example.gqsystem.databinding.SearchFragmentListBinding;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.ui.project.list.ProjectListFragment;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Logger;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.gqsystem.ui.company.content.CompanyContentFragment.PARAM1;
import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_CONTENT;

/**
 * @author devel
 */
public class SearchFragment extends BaseFragment<SearchFragmentListBinding, SearchViewModel> {

    private CommonAdapter<ProjectListBean.RecordsBean> commonAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.search_fragment_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }


    @Override
    protected void init() {
        initSearchView(mDataBinding.searchView);
        initRadioGroup();
        initHistoryData();
        initSearchListener();

        initRefreshLayout();
        initRecyclerView();
    }

    /**
     * 设置searchView的样式
     */
    private void initSearchView(SearchView searchView) {
        //去掉search View的下划线
        if (searchView != null) {
            try {
                Class<?> argClass = searchView.getClass();
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                ownField.setAccessible(true);
                View mView = (View) ownField.get(searchView);
                mView.setBackgroundResource(R.drawable.round_rectangle_white);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initRadioGroup() {
        mDataBinding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.btn1:
                    mViewModel.type.setValue(1);
                    if (!mDataBinding.searchView.getQuery().toString().isEmpty()) {
                        mViewModel.loadData();
                    }
                    break;
                case R.id.btn2:
                    mViewModel.type.setValue(2);
                    if (!mDataBinding.searchView.getQuery().toString().isEmpty()) {
                        mViewModel.loadData();
                    }

                    break;
                case R.id.btn3:
                    mViewModel.type.setValue(3);
                    if (!mDataBinding.searchView.getQuery().toString().isEmpty()) {
                        mViewModel.loadData();
                    }
                    break;
                default:
                    break;
            }
        });
    }

    private void initHistoryData() {
        FlexboxLayoutManager manager = new FlexboxLayoutManager(getContext());
        manager.setFlexDirection(FlexDirection.ROW);
        manager.setFlexWrap(FlexWrap.WRAP);
        mDataBinding.recyclerViewHistory.setLayoutManager(manager);

        CommonAdapter<SearchHistoryBean> historyAdapter = new CommonAdapter<SearchHistoryBean>(R.layout.search_item_history, BR.searchHistory) {
            @Override
            public void addListener(View root, SearchHistoryBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.tv_name).setOnClickListener(v -> {
                    switch (itemData.getType()) {
                        case 1:
                            mDataBinding.radioGroup.check(R.id.btn1);
                            break;
                        case 2:
                            mDataBinding.radioGroup.check(R.id.btn2);
                            break;
                        case 3:
                            mDataBinding.radioGroup.check(R.id.btn3);
                            break;
                        default:
                            break;
                    }
                    mDataBinding.searchView.setQuery(itemData.getSearchName(), false);
                });
            }
        };
        mDataBinding.recyclerViewHistory.setAdapter(historyAdapter);
        mViewModel.getSearchHistoryList().observe(this, historyAdapter::onItemDatasChanged);
    }


    /**
     * 搜索监听
     */
    private void initSearchListener() {
        mDataBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //向数据库中插入一条数据
                SearchHistoryBean searchHistoryBean = new SearchHistoryBean(mViewModel.type.getValue(), query);
                mViewModel.insertData(searchHistoryBean);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    mDataBinding.llHistory.setVisibility(View.VISIBLE);
                    mDataBinding.recyclerViewHistory.setVisibility(View.VISIBLE);
                    mViewModel.loadState.postValue(LoadState.NO_DATA);
                } else {
                    mViewModel.setSearchText(newText);
                }
                return false;
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        mDataBinding.refreshLayout.setPrimaryColorsId(android.R.color.white, R.color.colorPrimary);
        mDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(getContext())));
        mDataBinding.refreshLayout.setOnRefreshListener(refresh -> mViewModel.refreshData(true));
        mDataBinding.refreshLayout.setOnLoadMoreListener(refresh -> mViewModel.refreshData(false));
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        commonAdapter = new CommonAdapter<ProjectListBean.RecordsBean>(R.layout.search_item_project_layout, BR.project) {
            @Override
            public void addListener(View root, ProjectListBean.RecordsBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.item).setOnClickListener(v -> {
                    //项目详情
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(PARAM_PRO_CONTENT, itemData);
                    NavHostFragment.findNavController(SearchFragment.this).navigate(R.id.project_content, bundle);
                });

                root.findViewById(R.id.tv_company_name).setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString(PARAM1, itemData.getComId());
                    NavHostFragment.findNavController(SearchFragment.this).navigate(R.id.company_content, bundle);
                });
            }
        };
        mDataBinding.recyclerViewList.setAdapter(commonAdapter);
        mDataBinding.recyclerViewList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 更新数据
     */
    @Override
    protected void initDataChange() {
        mViewModel.getProjectList().observe(this, projectListBean -> {
            if (projectListBean.getTotal() != 0) {
                mDataBinding.llHistory.setVisibility(View.GONE);
                mDataBinding.recyclerViewHistory.setVisibility(View.GONE);
            }
            //刷新完成，更新数据
            if (projectListBean.getCurrent() >= projectListBean.getPages()) {
                mDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
            mDataBinding.refreshLayout.finishRefresh();
            mDataBinding.refreshLayout.finishLoadMore();
            if (commonAdapter != null) {
                commonAdapter.onItemDatasChanged(projectListBean.getRecords());
            }
        });
    }

}
