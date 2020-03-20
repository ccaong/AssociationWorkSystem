package com.example.gqsystem.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.gqsystem.App;
import com.example.gqsystem.manager.MyActivityManager;

import java.util.List;

/**
 * 工具类
 *
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
     * 调用系统拨号界面拨打电话
     *
     * @param tel
     */
    public static void callPhone(String tel) {
        //拨打电话
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        Activity activity = MyActivityManager.getInstance().getCurrentActivity();
        activity.startActivity(intent);
    }


    /**
     * 添加联系人
     *
     * @param name
     * @param phoneNumber
     * @param email
     */
    public static void addContact(String name, String phoneNumber, String email) {
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();

        // 向RawContacts.CONTENT_URI空值插入，
        // 先获取Android系统返回的rawContactId
        // 后面要基于此id插入值
        Uri rawContactUri = App.getContext().getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        // 内容类型
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        App.getContext().getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();

        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // 联系人的电话号码
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
        // 电话类型
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        // 向联系人电话号码URI添加电话号码
        App.getContext().getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();

        if (email != null) {
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
            // 联系人的Email地址
            values.put(ContactsContract.CommonDataKinds.Email.DATA, email);
            // 电子邮件的类型
            values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
            // 向联系人Email URI添加Email数据
            App.getContext().getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }

        ToastUtils.showToast("联系人添加成功！");
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
