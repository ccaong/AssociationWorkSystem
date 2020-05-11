package com.example.gqsystem.ui.project.work.judge;

import android.os.Bundle;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.databinding.ProjectFragmentJudgeBinding;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import static com.example.gqsystem.ui.project.ProjectContentFragment.PARAM_PRO_ID;

/**
 * @author : devel
 * @date : 2020/4/7 10:04
 * @desc :
 */
public class ProjectWorkJudgeFragment extends BaseFragment<ProjectFragmentJudgeBinding, ProjectWorkJudgeViewModel> {

    private String id;
    private String filePos;

    @Override
    protected void handleArguments(Bundle args) {
        super.handleArguments(args);
        id = args.getString(PARAM_PRO_ID, "");
    }

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.project_fragment_judge;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(ProjectWorkJudgeViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
        mDataBinding.setClick(new OpenFileListener());
    }

    @Override
    protected void init() {
        mViewModel.setId(id);
        mViewModel.loadData();
    }

    @Override
    protected void initDataChange() {
        mViewModel.getDownLoadProgress().observe(this, integer -> {
            switch (filePos) {
                case "1":
                    mViewModel.progress1.postValue(integer);
                    break;
                case "2":
                    mViewModel.progress2.postValue(integer);
                    break;
                case "3":
                    mViewModel.progress3.postValue(integer);
                    break;
                case "4":
                    mViewModel.progress4.postValue(integer);
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * 点击事件
     */
    public class OpenFileListener {

        /**
         * 打开签字盖章页面
         */
        public void btnSignStampPage(String fileName) {
            filePos = "1";
            requestPermission(fileName);
        }

        /**
         * 打开文件
         */
        public void btnJudgeReportFile(String fileName) {
            filePos = "2";

            requestPermission(fileName);
        }


        /**
         * 打开文件
         */
        public void btnSubmitFile(String fileName) {
            filePos = "3";
            requestPermission(fileName);
        }

        /**
         * 打开文件
         */
        public void btnOtherFile(String fileName) {
            filePos = "4";
            requestPermission(fileName);
        }

    }
}
