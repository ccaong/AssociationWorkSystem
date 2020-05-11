package com.example.gqsystem.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gqsystem.R;
import com.example.gqsystem.databinding.ViewNoNetworkBinding;

import androidx.databinding.DataBindingUtil;

/**
 * @author devel
 */
public class LoadingLayout extends FrameLayout {
    private Context mContext;

    private View mMainPage;
    private View loadingPage;
    private View errorPage;
    private View emptyPage;

    private TextView errorText;


    private int mErrorResId;
    private int mEmptyResId;
    private String mErrorStr;
    private String mEmptyStr;
    private String mLoadingStr;

    private ViewNoNetworkBinding mViewNoNetworkBinding;


    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.LoadingLayout, 0, 0);
            mErrorResId = a.getResourceId(R.styleable.LoadingLayout_loading_layout_error_img, R.mipmap.ic_load_error);
            mErrorStr = a.getString(R.styleable.LoadingLayout_loading_layout_error_text);
            mEmptyResId = a.getResourceId(R.styleable.LoadingLayout_loading_layout_empty_img, R.mipmap.ic_nodata);
            mEmptyStr = a.getString(R.styleable.LoadingLayout_loading_layout_empty_text);
            mLoadingStr = a.getString(R.styleable.LoadingLayout_loading_layout_loading_text);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        if (childCount == 0) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        if (childCount > 1) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }

        mMainPage = getChildAt(0);
        mMainPage.setVisibility(GONE);

        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        loadingPage = inflater.inflate(R.layout.view_loading, null);
        errorPage = inflater.inflate(R.layout.view_load_error, null);
        emptyPage = inflater.inflate(R.layout.view_no_data, null);

        errorText = errorPage.findViewById(R.id.tv_load_error);

        if (mErrorStr != null) {
            errorText.setText(mErrorStr);
        }

        this.addView(loadingPage);
        this.addView(errorPage);
        this.addView(emptyPage);

        loadingPage.setVisibility(GONE);
        errorPage.setVisibility(GONE);
        emptyPage.setVisibility(GONE);
    }


    /**
     * 设置错误布局文字
     *
     * @param errorStr
     */
    public void setErrorText(String errorStr) {
        if (!TextUtils.isEmpty(errorStr)) {
            errorText.setText(errorStr);
            this.mErrorStr = errorStr;
        }
    }

    /**
     * 设置错误布局文字
     *
     * @param resId
     */
    public void setErrorText(int resId) {
        String string = mContext.getResources().getString(resId);
        if (!TextUtils.isEmpty(string)) {
            errorText.setText(string);
            this.mErrorStr = string;
        }
    }

    /**
     * 显示加载布局
     */
    public void showLoading() {
        loadingPage.setVisibility(VISIBLE);
        errorPage.setVisibility(GONE);
        emptyPage.setVisibility(GONE);
        mMainPage.setVisibility(GONE);
    }

    /**
     * 显示错误布局
     */
    public void showError() {
        loadingPage.setVisibility(GONE);
        errorPage.setVisibility(VISIBLE);
        emptyPage.setVisibility(GONE);
        mMainPage.setVisibility(GONE);
    }

    /**
     * 显示空布局
     */
    public void showEmpty() {
        loadingPage.setVisibility(GONE);
        errorPage.setVisibility(GONE);
        emptyPage.setVisibility(VISIBLE);
        mMainPage.setVisibility(GONE);
    }

    /**
     * 显示内容布局
     */
    public void showMain() {
        loadingPage.setVisibility(GONE);
        errorPage.setVisibility(GONE);
        emptyPage.setVisibility(GONE);
        mMainPage.setVisibility(VISIBLE);
    }

    /**
     * 设置Error点击事件
     *
     * @param listener
     */
    public void setErrorClickListener(OnClickListener listener) {
        if (listener != null) {
            findViewById(R.id.layout_error).setOnClickListener(listener);
        }
    }
}
