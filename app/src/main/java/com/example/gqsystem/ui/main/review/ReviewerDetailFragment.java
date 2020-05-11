package com.example.gqsystem.ui.main.review;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.databinding.ReviewerFragmentDetailBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.ToastUtils;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author : devel
 * @date : 2020/4/10 10:17
 * @desc :
 */
public class ReviewerDetailFragment extends BaseFragment<ReviewerFragmentDetailBinding, ReviewerViewModel> {

    private CommonAdapter<FileBean> fileAdapter;
    private int pos;
    private FileBean bean;

    @Override
    protected int getLayoutResId() {
        return R.layout.reviewer_fragment_detail;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(getActivity()).get(ReviewerViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
        mDataBinding.setClick(new EventListener());
    }

    @Override
    protected void init() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        fileAdapter = new CommonAdapter<FileBean>(R.layout.project_item_content_file, com.example.gqsystem.BR.fileProject) {
            @Override
            public void addListener(View root, FileBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.item).setOnClickListener(v -> {
                    bean = itemData;
                    pos = position;
                    requestPermission(itemData.getFileName());
                });
            }
        };
        mDataBinding.rvFile.setAdapter(fileAdapter);
        mDataBinding.rvFile.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    /**
     * 更新数据
     */
    @Override
    protected void initDataChange() {
        mViewModel.getFileList().observe(this, list -> {
            if (fileAdapter != null) {
                fileAdapter.onItemDatasChanged(list);
            }
        });

        mViewModel.getDownLoadProgress().observe(this, progress -> {
            if (bean != null && fileAdapter != null) {
                bean.setDownloadProgress(progress);
                fileAdapter.notifyItemChanged(pos);
            }
        });
    }


    /**
     * 点击事件
     */
    public class EventListener {

        /**
         * 复制信息
         */
        public void btnCopyInfo(String info) {
            CommonUtils.setClipoard(info);
            ToastUtils.showToast("信息已经复制到系统粘贴板");
        }

        /**
         * 拨打电话
         */
        public void btnNavigation(String tel) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + tel);
            intent.setData(data);
            startActivity(intent);
        }

    }
}
