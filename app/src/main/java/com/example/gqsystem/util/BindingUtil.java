package com.example.gqsystem.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gqsystem.App;
import com.example.gqsystem.R;
import com.example.gqsystem.enums.LoadState;
import com.example.gqsystem.view.CircleImageView;
import com.example.gqsystem.view.LoadingLayout;

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

    @BindingAdapter("loadImageWithDefault")
    public static void setImageUrlWithDefault(ImageView imageView, String url) {
        GlideUtil.loadImageWithDefault(imageView, url);
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


    /**
     * 性别
     *
     * @param textView
     * @param i
     */
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


    /**
     * 头像
     *
     * @param ivHeader
     * @param i
     */
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


    /**
     * 关联企业关系
     *
     * @param textView
     * @param type
     */
    @BindingAdapter("companyRelated")
    public static void setCompanyRelated(TextView textView, String type) {
        if (type == null) {
            textView.setText("未知关系");
            return;
        }
        switch (type) {
            case "1":
                textView.setText("母公司");
                break;
            case "2":
                textView.setText("子公司");
                break;
            case "3":
                textView.setText("兄弟企业");
                break;
            case "4":
                textView.setText("其他关联");
                break;
            case "5":
                textView.setText("备注");
                break;
            default:
                textView.setText("未知关系");
                break;
        }
    }


    /**
     * 文件名称
     *
     * @param textView
     * @param filePath
     */
    @BindingAdapter("fileName")
    public static void setFileName(TextView textView, String filePath) {
        if (filePath == null) {
            textView.setText("没有文件");
            return;
        }
        String fileName = filePath;
        int i = filePath.lastIndexOf('/');
        if (i != -1) {
            fileName = filePath.substring(i + 1);
        }

        int n = fileName.lastIndexOf("_");
        if (n != -1) {
            fileName = fileName.substring(0, n);
        }
        textView.setText(fileName);
    }

    /**
     * 文件类型
     *
     * @param imageView
     * @param filePath
     */
    @BindingAdapter("fileType")
    public static void setFileType(ImageView imageView, String filePath) {
        if (filePath == null) {
            return;
        }

        int i = filePath.lastIndexOf(".");
        if (i < 0) {
            imageView.setImageResource(R.mipmap.ic_file_other);
        } else {
            switch (filePath.substring(i + 1)) {
                case "doc":
                case "docx":
                    imageView.setImageResource(R.mipmap.ic_file_word);
                    break;
                case "pdf":
                    imageView.setImageResource(R.mipmap.ic_file_pdf);
                    break;
                case "xls":
                case "xlsx":
                    imageView.setImageResource(R.mipmap.ic_file_excel);
                    break;
                case "ppt":
                case "pptx":
                    imageView.setImageResource(R.mipmap.ic_file_ppt);
                    break;
                case "jpg":
                case "jpeg":
                case "png":
                case "gif":
                    imageView.setImageResource(R.mipmap.ic_file_jpg);
                    break;
                case "txt":
                    imageView.setImageResource(R.mipmap.ic_file_txt);
                    break;
                case "wav":
                case "wma":
                case "mp3":
                    imageView.setImageResource(R.mipmap.ic_file_music);
                    break;
                case "mp4":
                case "wmv":
                    imageView.setImageResource(R.mipmap.ic_file_video);
                    break;
                case "zip":
                case "7z":
                case "rar":
                    imageView.setImageResource(R.mipmap.ic_file_zip);
                    break;
                default:
                    imageView.setImageResource(R.mipmap.ic_file_other);
                    break;
            }
        }
    }


    /**
     * 文件名称.文件类型
     *
     * @param textView
     * @param filePath
     */
    @BindingAdapter("fileNameWithType")
    public static void setFileNameWithType(TextView textView, String filePath) {
        if (filePath == null) {
            textView.setText("没有文件");
            return;
        }
        int first = filePath.indexOf(',');
        if (first != -1) {
            filePath = filePath.substring(0, first);
        }

        String fileName = filePath;
        int i = filePath.lastIndexOf('/');
        if (i != -1) {
            fileName = filePath.substring(i + 1);
        }

        int n = fileName.lastIndexOf("_");
        if (n != -1) {
            fileName = fileName.substring(0, n);
        }
        int m = filePath.lastIndexOf(".");
        String type = filePath.substring(m + 1);
        textView.setText(fileName + "." + type);
    }


    /**
     * 文件是否已经下载
     *
     * @param imageView
     * @param fileName
     */
    @BindingAdapter("downloadStatus")
    public static void setDownLoadStatus(ImageView imageView, String fileName) {
        if (FileUtil.fileIsDownload(fileName)) {
            imageView.setVisibility(View.GONE);
//            imageView.setImageResource(R.mipmap.ic_cloud_check);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.icon_net_cloud_download);
        }
    }


    /**
     * 文件下载progress
     *
     * @param textView
     * @param progress
     */
    @BindingAdapter("downloadProgress")
    public static void setDownLoadProgress(TextView textView, int progress) {
        if (progress != 0) {
            textView.getBackground().setLevel(progress);
        }
    }

    /**
     * loadingLayout
     *
     * @param layout
     * @param loadState
     */
    @BindingAdapter("loadingStatus")
    public static void setLoadingLayoutStatus(LoadingLayout layout, LoadState loadState) {
        if (layout == null) {
            return;
        }
        if (loadState == null) {
            return;
        }
        switch (loadState) {
            case LOADING:
                layout.showLoading();
                break;
            case NO_NETWORK:
                layout.setErrorText("没有网络！");
                layout.showError();
                break;
            case NO_DATA:
                layout.showEmpty();
                break;
            case ERROR:
                layout.showError();
                break;
            case SUCCESS:
                layout.showMain();
                break;
            default:
                layout.showMain();
                break;
        }
    }

    /**
     * loadingLayout
     *
     * @param layout
     * @param str
     */
    @BindingAdapter("loadingErrorMsg")
    public static void setLoadingLayoutErrorMag(LoadingLayout layout, String str) {
        if (layout == null) {
            return;
        }
        layout.setErrorText(str);
    }


    /**
     * 发票开具情况
     *
     * @param textView
     * @param str
     */
    @BindingAdapter("invoiceStatus")
    public static void setInvoiceStatus(TextView textView, String str) {
        if ("1".equals(str)) {
            textView.setText("未开具");
        } else {
            textView.setText("已开具");
        }
    }


    /**
     * 项目状态
     *
     * @param textView
     * @param status
     */
    @BindingAdapter("proStatus")
    public static void setProjectStatus(TextView textView, String status) {
        if (status == null) {
            textView.setText("待开发");
            return;
        }
        switch (status) {
            case "1":
                textView.setText("待开发");
                break;
            case "2":
                textView.setText("咨询中");
                break;
            case "3":
                textView.setText("待评审");
                break;
            case "4":
                textView.setText("待整改");
                break;
            case "5":
                textView.setText("待发证");
                break;
            case "6":
                textView.setText("已结束");
                break;
            case "7":
                textView.setText("编制中");
                break;
            case "8":
                textView.setText("洽谈中");
                break;
            case "9":
                textView.setText("运维中");
                break;
            default:
                textView.setText("其他状态");
                break;
        }
    }

    /**
     * 政府项目文件类型
     *
     * @param textView
     * @param status
     */
    @BindingAdapter("govInfoType")
    public static void setGovInfoType(TextView textView, String status) {
        if (status == null) {
            textView.setText("其他材料");
            return;
        }
        switch (status) {
            case "1":
                textView.setText("招标资料");
                break;
            case "2":
                textView.setText("投标资料");
                break;
            case "3":
                textView.setText("工作方案");
                break;
            case "4":
                textView.setText("开工前准备资料");
                break;
            case "5":
                textView.setText("时间计划安排");
                break;
            case "6":
                textView.setText("签字资料");
                break;
            case "7":
                textView.setText("影像资料");
                break;
            case "8":
                textView.setText("各类总结报告");
                break;
            case "9":
                textView.setText("其他材料");
                break;
            default:
                textView.setText("其他类型材料");
                break;
        }
    }


    /**
     * 沟通方式
     *
     * @param imageView
     * @param type
     */
    @BindingAdapter("communicationType")
    public static void setCommunicationType(ImageView imageView, String type) {
        if (type == null) {
            imageView.setImageResource(R.mipmap.ic_talk);
            return;
        }
        switch (type) {
            case "1":
                imageView.setImageResource(R.mipmap.ic_phone);
                break;
            case "2":
                imageView.setImageResource(R.mipmap.ic_wechat);
                break;
            case "3":
                imageView.setImageResource(R.mipmap.ic_email);
                break;
            case "4":
                imageView.setImageResource(R.mipmap.ic_talk);
                break;
            default:
                imageView.setImageResource(R.mipmap.ic_talk);
                break;
        }
    }


    /**
     * 步骤
     *
     * @param textView
     * @param step
     */
    @BindingAdapter("assessStep")
    public static void setAssessStep(TextView textView, String step) {
        if (step == null) {
            textView.setText("其他步骤");
            return;
        }
        switch (step) {
            case "1":
                textView.setText("现场查看");
                break;
            case "2":
                textView.setText("提供资料清单");
                break;
            case "3":
                textView.setText("企业反馈材料齐全");
                break;
            case "4":
                textView.setText("开始编制");
                break;
            case "5":
                textView.setText("验收");
                break;
            case "6":
                textView.setText("内部审核");
                break;
            case "7":
                textView.setText("技术审核");
                break;
            case "8":
                textView.setText("企业较对");
                break;
            case "9":
                textView.setText("材料定稿");
                break;
            case "10":
                textView.setText("打印邮寄");
                break;
            case "11":
                textView.setText("企业反馈");
                break;
            default:
                textView.setText("其他步骤");
                break;
        }
    }
}
