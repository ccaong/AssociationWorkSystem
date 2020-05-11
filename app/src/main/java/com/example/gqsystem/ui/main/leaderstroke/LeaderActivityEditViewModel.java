package com.example.gqsystem.ui.main.leaderstroke;

import android.graphics.Color;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.gqsystem.App;
import com.example.gqsystem.base.viewmodel.BaseViewModel;
import com.example.gqsystem.bean.CityBean;
import com.example.gqsystem.bean.response.LeaderActivityListBean;
import com.example.gqsystem.http.data.HttpDisposable;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.GetJsonDataUtil;
import com.example.gqsystem.util.TimeUtils;
import com.example.gqsystem.util.ToastUtils;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author : devel
 * @date : 2020/4/14 13:58
 * @desc :
 */
public class LeaderActivityEditViewModel extends BaseViewModel {

    private MutableLiveData<Integer> addSuccess;

    private MutableLiveData<String> activityId;
    public MutableLiveData<String> activityName;
    public MutableLiveData<String> activityContent;

    public MutableLiveData<String> activityAddress;
    public MutableLiveData<String> addressDetails;

    public MutableLiveData<String> activityTime;
    public MutableLiveData<String> invitationUnit;


    public LeaderActivityEditViewModel() {
        addSuccess = new MutableLiveData<>();

        activityId = new MutableLiveData<>();

        activityName = new MutableLiveData<>();
        activityContent = new MutableLiveData<>();
        activityAddress = new MutableLiveData<>("请点击选择活动地点");
        addressDetails = new MutableLiveData<>();
        activityTime = new MutableLiveData<>("请点击选择活动时间");
        invitationUnit = new MutableLiveData<>();

        initJsonData();
    }


    public LiveData<Integer> getAddSuccess() {
        return addSuccess;
    }

    /**
     * 编辑
     *
     * @param bean
     */
    public void editActity(LeaderActivityListBean.RecordsBean bean) {
        if (bean != null) {
            activityId.postValue(bean.getId());
            activityName.postValue(bean.getActivityName());
            activityContent.postValue(bean.getActivityContent());
            activityAddress.postValue(bean.getActivityAddress());
            addressDetails.postValue(bean.getAddressDetails());
            activityTime.postValue(bean.getActivityTime());
            invitationUnit.postValue(bean.getInvitationUnit());
        }
    }

    private List<CityBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<CityBean.ChildrenBeanX.ChildrenBean>>> options3Items = new ArrayList<>();

    /**
     * 初始化省市区数据
     */
    private void initJsonData() {
        //获取assets目录下的json文件数据
        String JsonData = new GetJsonDataUtil().getJson(App.getContext(), "cities.json");
        ArrayList<CityBean> jsonBean = parseData(JsonData);

        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {
            //遍历省份
            ArrayList<String> cityList = new ArrayList<>();
            //该省的城市列表（第二级）
            ArrayList<ArrayList<CityBean.ChildrenBeanX.ChildrenBean>> province_AreaList = new ArrayList<>();
            //该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getChildren().size(); c++) {
                //遍历该省份的所有城市
                String cityName = jsonBean.get(i).getChildren().get(c).getValue();
                cityList.add(cityName);
                //添加城市
                ArrayList<CityBean.ChildrenBeanX.ChildrenBean> city_AreaList = new ArrayList<>();
                //该城市的所有地区列表
                if (jsonBean.get(i).getChildren().get(c).getChildren() != null) {
                    city_AreaList.addAll(jsonBean.get(i).getChildren().get(c).getChildren());
                }
                province_AreaList.add(city_AreaList);
                //添加该省所有地区数据
            }

            //添加城市数据
            options2Items.add(cityList);
            //添加地区数据
            options3Items.add(province_AreaList);
        }
    }

    /**
     * 解析城市json数据
     *
     * @param result
     * @return
     */
    private ArrayList<CityBean> parseData(String result) {
        //Gson 解析
        ArrayList<CityBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                CityBean entity = gson.fromJson(data.optJSONObject(i).toString(), CityBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    /**
     * 选择时间
     */
    public void selectTime() {
        TimePickerView pvTime = new TimePickerBuilder(MyActivityManager.getInstance().getCurrentActivity(),
                (date, v) -> activityTime.postValue(TimeUtils.date2String(date, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()))))
                .setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .setOutSideCancelable(false)
                .isCyclic(true)
                .setLabel("-", "-", " ", ":", "", "秒")
                .isCenterLabel(true)
                .build();
        pvTime.show();
    }

    /**
     * 选择地点
     */
    public void selectAddress() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(MyActivityManager.getInstance().getCurrentActivity(), (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String opt1tx = options1Items.size() > 0 ?
                    options1Items.get(options1).getPickerViewText() : "";

            String opt2tx = options2Items.size() > 0
                    && options2Items.get(options1).size() > 0 ?
                    options2Items.get(options1).get(options2) : "";

            String opt3tx = options2Items.size() > 0
                    && options3Items.get(options1).size() > 0
                    && options3Items.get(options1).get(options2).size() > 0 ?
                    options3Items.get(options1).get(options2).get(options3).getValue() : "";

            if (!CommonUtils.isStringEmpty(opt3tx)) {
                opt3tx = "," + opt3tx;
            }
            String address = opt1tx + "," + opt2tx + opt3tx;
            activityAddress.postValue(address);
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();
    }


    /**
     * 提交
     */
    public void submit() {

        if (CommonUtils.isStringEmpty(activityId.getValue())) {
            addLeaderActivity();
        } else {
            editLeaderActivity();
        }
    }

    /**
     * 新增
     */
    private void addLeaderActivity() {
        if (CommonUtils.isStringEmpty(activityName.getValue())) {
            ToastUtils.showToast("请填写活动名称！");
            return;
        }
        if ("请点击选择活动地点".equals(activityAddress.getValue())) {
            ToastUtils.showToast("请点击选择活动地点！");
            return;
        }

        if ("请点击选择活动时间".equals(activityTime.getValue())) {
            ToastUtils.showToast("请点击选择活动时间！");
            return;
        }
        Map map = new HashMap();
        map.put("activityName", activityName.getValue());
        map.put("activityContent", activityContent.getValue());
        map.put("activityAddress", activityAddress.getValue());
        map.put("addressDetails", addressDetails.getValue());
        map.put("activityTime", activityTime.getValue());
        map.put("invitationUnit", invitationUnit.getValue());

        HttpRequest.getInstance()
                .addLeaderActivity(map)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<Object>() {
                    @Override
                    public void success(Object bean) {
                        addSuccess.postValue(1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addSuccess.postValue(-1);
                    }
                });
    }


    /**
     * 编辑
     */
    private void editLeaderActivity() {
        if (CommonUtils.isStringEmpty(activityName.getValue())) {
            ToastUtils.showToast("请填写活动名称！");
            return;
        }
        Map map = new HashMap();
        map.put("id", activityId.getValue());
        map.put("activityName", activityName.getValue());
        map.put("activityContent", activityContent.getValue());
        map.put("activityAddress", activityAddress.getValue());
        map.put("addressDetails", addressDetails.getValue());
        map.put("activityTime", activityTime.getValue());
        map.put("invitationUnit", invitationUnit.getValue());

        HttpRequest.getInstance()
                .deleteLeaderActivity(map)
                .compose(HttpFactory.schedulers())
                .subscribe(new HttpDisposable<Object>() {
                    @Override
                    public void success(Object bean) {
                        addSuccess.postValue(1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addSuccess.postValue(-1);
                    }
                });
    }
}
