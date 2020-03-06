package com.example.gqsystem.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
}
