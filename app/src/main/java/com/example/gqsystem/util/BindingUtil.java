package com.example.gqsystem.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gqsystem.App;
import com.example.gqsystem.R;
import com.example.gqsystem.view.CircleImageView;

import androidx.databinding.BindingAdapter;

/**
 * @author : devel
 * @date : 2020/3/5 15:29
 * @desc :
 */
public class BindingUtil {

    @BindingAdapter("int2String")
    public static void int2String(TextView textView, int i) {
        textView.setText("" + i);
    }

    @BindingAdapter("loadImage")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url != null) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }
    }

    @BindingAdapter("homeItemNotice")
    public static void setHomeNotice(TextView textView, int i) {
        if (i == 0) {
            textView.setVisibility(View.GONE);
        } else if (i < 10) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(i + "");
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText("9+");
        }
    }


    @BindingAdapter("personSex")
    public static void setPersonSex(TextView textView, int i) {
        if (i == 1) {
            Drawable drawable = App.getContext().getResources().getDrawable(R.mipmap.ic_gender_male);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
        } else {
            Drawable drawable = App.getContext().getResources().getDrawable(R.mipmap.ic_gender_female);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }


    @BindingAdapter("userHeader")
    public static void setUserHeader(CircleImageView ivHeader, int i) {
        if (i == 1) {
            ivHeader.setImageResource(R.mipmap.header_male);
        } else {
            ivHeader.setImageResource(R.mipmap.header_female);
        }
    }

    /**
     * 发票类型
     *
     * @param textView
     * @param type
     */
    @BindingAdapter("invoiceType")
    public static void setInvoiceType(TextView textView, String type) {
        if ("1".equals(type)) {
            textView.setText("增值税专用发票");
        } else {
            textView.setText("增值税普通发票");
        }
    }

    /**
     * 公司类型
     *
     * @param textView
     * @param type
     */
    @BindingAdapter("companyType")
    public static void setCompanyType(TextView textView, String type) {
        if (type == null) {
            textView.setText("未知");
            return;
        }

        switch (type) {
            case "1":
                textView.setText("央企");
                break;
            case "2":
                textView.setText("国企");
                break;
            case "3":
                textView.setText("民营");
                break;
            case "4":
                textView.setText("股份制");
                break;
            case "5":
                textView.setText("其他");
                break;
            default:
                textView.setText("未知");
                break;
        }
    }


    /**
     * 上市情况
     *
     * @param textView
     * @param type
     */
    @BindingAdapter("companyMarket")
    public static void setCompanyMarket(TextView textView, String type) {
        if (type == null) {
            textView.setText("未知");
            return;
        }
        switch (type) {
            case "1":
                textView.setText("主板");
                break;
            case "2":
                textView.setText("创业板");
                break;
            case "3":
                textView.setText("中小板");
                break;
            case "4":
                textView.setText("新三板");
                break;
            case "5":
                textView.setText("未上市");
                break;
            case "6":
                textView.setText("其他");
                break;
            default:
                textView.setText("未知");
                break;
        }
    }


    /**
     * 关系类型
     *
     * @param textView
     * @param type
     */
    @BindingAdapter("companyRelation")
    public static void setCompanyRelation(TextView textView, String type) {
        if (type == null) {
            textView.setText("未知关系类型");
            return;
        }
        String relation = type.replace("1", "强关系");
        relation = relation.replace("2", "一般关系");
        relation = relation.replace("3", "弱关系");
        relation = relation.replace("4", "其他关系");
        textView.setText(relation);

    }

    /**
     * 专项整治
     *
     * @param textView
     * @param type
     */
    @BindingAdapter("comSpecialReform")
    public static void setSpecialReform(TextView textView, String type) {
        if (type == null) {
            textView.setText("不涉及专项整治");
            return;
        }
        String relation = type.replace("1", "涉氨制冷");
        relation = relation.replace("2", "有限空间");
        relation = relation.replace("3", "粉尘防爆");
        relation = relation.replace("4", "金属冶炼");
        relation = relation.replace("5", "其他");
        textView.setText(relation);

    }


    /**
     * 企业联系人职务
     *
     * @param textView
     * @param type
     */
    @BindingAdapter("constantPosition")
    public static void setConstantPosition(TextView textView, String type) {
        if (type == null) {
            textView.setText("未知职务");
            return;
        }
        switch (type) {
            case "1":
                textView.setText("企业联系人");
                break;
            case "2":
                textView.setText("主要负责人");
                break;
            case "3":
                textView.setText("分管安全副总");
                break;
            case "4":
                textView.setText("安全部门负责人");
                break;
            case "5":
                textView.setText("安全管理部门人员");
                break;
            default:
                textView.setText("未知");
                break;
        }
    }


}
