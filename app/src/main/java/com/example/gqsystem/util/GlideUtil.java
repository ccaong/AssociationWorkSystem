package com.example.gqsystem.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.gqsystem.App;
import com.example.gqsystem.R;

/**
 * @author : devel
 * @date : 2020/2/28 16:50
 * @desc :
 */
public class GlideUtil {


    public static void loadImage(ImageView imageView, Object url) {
        Glide.with(App.getContext())
                .load(url)
                .into(imageView);
    }

    public static void loadImage(ImageView imageView, String url, int defaultImage) {
        RequestOptions options = new RequestOptions();
        options.placeholder(defaultImage)
                .error(defaultImage).fallback(defaultImage);

        Glide.with(App.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadImageWithDefault(ImageView imageView, String url) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_load_error)
                .error(R.mipmap.ic_load_error).fallback(R.mipmap.ic_load_error);

        Glide.with(App.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }
//
//
//    public static void loadImageWithGoss(ImageView imageView, String url) {
//        Glide.with(App.getContext())
//                .load(url)
//                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
//                .into(imageView);
//    }
}
