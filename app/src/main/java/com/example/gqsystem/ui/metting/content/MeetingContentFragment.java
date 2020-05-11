package com.example.gqsystem.ui.metting.content;

import android.view.View;

import com.example.gqsystem.BR;
import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.bean.FileBean;
import com.example.gqsystem.databinding.MeetingFragmentContentBinding;
import com.example.gqsystem.ui.adapter.CommonAdapter;
import com.example.gqsystem.ui.metting.list.MeetingViewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


/**
 * @author devel
 */
public class MeetingContentFragment extends BaseFragment<MeetingFragmentContentBinding, MeetingViewModel> {

    private CommonAdapter<FileBean> fileAdapter;

    private int pos;
    private FileBean fileBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.meeting_fragment_content;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(getActivity()).get(MeetingViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

        initRecyclerView();
    }

    private void initRecyclerView() {

        fileAdapter = new CommonAdapter<FileBean>(R.layout.meeting_item_file, BR.file) {
            @Override
            public void addListener(View root, FileBean itemData, int position) {
                super.addListener(root, itemData, position);
                root.findViewById(R.id.item).setOnClickListener(v -> {
                    pos = position;
                    fileBean = itemData;
                    requestPermission(itemData.getFileName());
                });

            }
        };
        mDataBinding.recyclerView.setAdapter(fileAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    protected void initDataChange() {
        mViewModel.getMeetingFileList().observe(this, files -> {
            if (fileAdapter != null) {
                fileAdapter.onItemDatasChanged(files);
            }
        });

        mViewModel.getDownLoadProgress().observe(this, progress -> {
            if (fileBean != null && fileAdapter != null) {
                fileBean.setDownloadProgress(progress);
                fileAdapter.notifyItemChanged(pos);
            }
        });
    }
}
