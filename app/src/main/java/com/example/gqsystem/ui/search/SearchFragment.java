package com.example.gqsystem.ui.search;

import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.SearchHistoryBean;
import com.example.gqsystem.databinding.SearchFragmentListBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.lang.reflect.Field;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author devel
 */
public class SearchFragment extends BaseFragment<SearchFragmentListBinding, SearchViewModel> {


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
            //直接打开
//            searchView.setIconifiedByDefault(false);
        }
    }

    private void initRadioGroup() {
        mDataBinding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.btn1:
                    mViewModel.type.postValue(1);
                    break;
                case R.id.btn2:
                    mViewModel.type.postValue(2);
                    break;
                case R.id.btn3:
                    mViewModel.type.postValue(3);
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
                root.findViewById(R.id.tv_name).setOnClickListener(v -> mViewModel.search(itemData.getSearchName()));
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
                mViewModel.search(newText);
                return false;
            }
        });
    }
}
