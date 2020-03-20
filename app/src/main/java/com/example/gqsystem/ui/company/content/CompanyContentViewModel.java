package com.example.gqsystem.ui.company.content;

import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.response.CompanyBasisInfoBean;
import com.example.gqsystem.bean.response.CompanyInstudry;
import com.example.gqsystem.bean.response.InvoiceInfoBean;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.util.ToastUtils;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author devel
 */
public class CompanyContentViewModel extends BaseViewModel {

    private Integer id;

    public MutableLiveData<CompanyBasisInfoBean> basisBean;
    public MutableLiveData<String> industryBean;
    private MutableLiveData<InvoiceInfoBean> mInvoiceInfoBean;

    public CompanyContentViewModel() {
        basisBean = new MutableLiveData<>();
        industryBean = new MutableLiveData<>();
        mInvoiceInfoBean = new MediatorLiveData<>();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LiveData<InvoiceInfoBean> getInvoiceInfoBean() {
        return mInvoiceInfoBean;
    }

    /**
     * 获取企业基本信息
     */
    public void loadCompanyInfo() {
        loadState.setValue(LoadState.LOADING);

        HttpRequest.getInstance()
                .getCompanyConstant(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<CompanyBasisInfoBean>() {
                    @Override
                    public void success(CompanyBasisInfoBean bean) {
                        loadState.setValue(LoadState.SUCCESS);
                        basisBean.postValue(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadState.setValue(LoadState.ERROR);
                    }
                });
    }

    /**
     * 获取企业所属行业信息
     */
    public void loadCompanyIndustry() {
        HttpRequest.getInstance()
                .queryComIndustryByMainId(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<CompanyInstudry>>() {
                    @Override
                    public void success(List<CompanyInstudry> bean) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (CompanyInstudry companyInstudry : bean) {
                            if (stringBuilder.length() == 0) {
                                stringBuilder.append(companyInstudry.getMain_name()).append("-").append(companyInstudry.getSmall_name());
                            } else {
                                stringBuilder.append("\n").append(companyInstudry.getMain_name()).append("-").append(companyInstudry.getSmall_name());
                            }
                        }
                        industryBean.postValue(stringBuilder.toString());
                    }
                });
    }


    /**
     * 获取企业开票信息
     */
    public void loadCompanyInvoice() {
        HttpRequest.getInstance()
                .getCompanyInvoice(id)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<List<InvoiceInfoBean>>() {
                    @Override
                    public void success(List<InvoiceInfoBean> bean) {
                        if (bean.size() == 1) {
                            mInvoiceInfoBean.postValue(bean.get(0));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast("系统没有收录该企业的开票信息！");
                    }
                });
    }

}
