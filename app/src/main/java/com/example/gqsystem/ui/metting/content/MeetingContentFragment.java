package com.example.gqsystem.ui.metting.content;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.MeetingFragmentContentBinding;

import androidx.lifecycle.ViewModelProvider;


public class MeetingContentFragment extends BaseFragment<MeetingFragmentContentBinding, MeetingContentViewModel> {


    public static MeetingContentFragment newInstance() {
        return new MeetingContentFragment();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.meeting_fragment_content;
    }

    @Override
    protected void initViewModel() {

        mViewModel = new ViewModelProvider(this).get(MeetingContentViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
