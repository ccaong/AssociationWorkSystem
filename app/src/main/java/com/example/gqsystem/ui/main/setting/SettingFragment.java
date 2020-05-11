package com.example.gqsystem.ui.main.setting;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseFragment;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.databinding.FragmentSettingBinding;
import com.orhanobut.hawk.Hawk;

import androidx.lifecycle.ViewModelProvider;

/**
 * @author devel
 */
public class SettingFragment extends BaseFragment<FragmentSettingBinding, SettingViewModel> {


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        mDataBinding.switchReader.setOnCheckedChangeListener((buttonView, isChecked) -> Hawk.put(Constants.SettingCode.OPEN_FILE_WITH_WPS, isChecked));
        mDataBinding.switchReaderType.setOnCheckedChangeListener((buttonView, isChecked) -> Hawk.put(Constants.SettingCode.READ_ONLY_TYPE, isChecked));
        mDataBinding.switchDownload.setOnCheckedChangeListener((buttonView, isChecked) -> Hawk.put(Constants.SettingCode.ALLOW_USE_DATA_DOWNLOAD, isChecked));

        mDataBinding.spinnerTimeOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("选择", "" + position + "ID:" + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}