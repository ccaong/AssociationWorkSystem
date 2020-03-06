package com.example.gqsystem.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.example.gqsystem.App;

import java.util.List;

/**
 * 工具类
 * @author devel
 */
public final class CommonUtils {

    private CommonUtils() {

    }

    /**
     * 复制文字到系统的剪贴板
     *
     * @param text
     */
    public static void setClipoard(String text) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) App.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }


    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return true表示为空，false表示不为空
     */
    public static boolean isListEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static boolean isStringEmpty(String string) {
        return string == null || "".equals(string);
    }
}
