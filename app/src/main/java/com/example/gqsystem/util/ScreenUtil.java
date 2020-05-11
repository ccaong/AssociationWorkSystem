package com.example.gqsystem.util;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;

public class ScreenUtil {


    private static int getNavBarHeight(final Resources resources) {
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return resources.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    private static DisplayMetrics getMetricsFromField(final Resources resources, final Field field) {
        try {
            return (DisplayMetrics) field.get(resources);
        } catch (Exception e) {
            Log.e("AdaptScreenUtils", "getMetricsFromField: " + e);
            return null;
        }
    }

    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    public static void setCustomDensity(Activity activity, final Application application) {
        DisplayMetrics systemDM = Resources.getSystem().getDisplayMetrics();

        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
//        if (sNoncompatDensity == 0) {
//            sNoncompatDensity = appDisplayMetrics.density;
//            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
//
//            application.registerComponentCallbacks(new ComponentCallbacks() {
//                @Override
//                public void onConfigurationChanged(@NonNull Configuration newConfig) {
//                    if (newConfig != null && newConfig.fontScale > 0) {
//                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
//                    }
//                }
//
//                @Override
//                public void onLowMemory() {
//                }
//            });
//        }

        final float targetDensity = appDisplayMetrics.widthPixels / 375;
//        final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
//        final int targetDensityDpi = (int) (160 * targetDensity);


//        appDisplayMetrics.density = targetDensity;
//        appDisplayMetrics.scaledDensity = targetScaledDensity;
//        appDisplayMetrics.densityDpi = targetDensityDpi;


        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = activityDisplayMetrics.widthPixels / 360;
        activityDisplayMetrics.scaledDensity = activityDisplayMetrics.density * (systemDM.scaledDensity / systemDM.density);
        activityDisplayMetrics.densityDpi = (int) (160 * activityDisplayMetrics.density);
    }



    public static void resetScreen(Activity activity) {
        //系统的屏幕尺寸
        DisplayMetrics systemDM = Resources.getSystem().getDisplayMetrics();
        //app整体的屏幕尺寸
        final DisplayMetrics appDM = activity.getApplication().getResources().getDisplayMetrics();
        //activity的屏幕尺寸
        final DisplayMetrics activityDM = activity.getResources().getDisplayMetrics();

        activityDM.density = systemDM.density;
        activityDM.scaledDensity = systemDM.scaledDensity;
        activityDM.densityDpi = systemDM.densityDpi;

        appDM.density = systemDM.density;
        appDM.scaledDensity = systemDM.scaledDensity;
        appDM.densityDpi = systemDM.densityDpi;
    }

}
