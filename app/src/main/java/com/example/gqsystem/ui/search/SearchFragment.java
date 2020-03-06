package com.example.gqsystem.ui.search;

import android.view.View;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.SearchFragmentListBinding;

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
}
