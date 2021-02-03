package com.arneca.evyap.helper;/*
 * Created by Sinan KUTAS on 22.01.2021.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.orhanobut.hawk.Hawk;

public class SharedPreferenceHelper {
    private static final String PREF_FILE = "PREF";

    public static void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences settting = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settting.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setSharedPreferenceInt(Context context, String key, int value) {
        SharedPreferences settting = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settting.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setSharedPreferenceBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);

        editor.apply();
    }

    public static String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getString(key, defValue);
    }

    public static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getBoolean(key, defValue);
    }

    public static int getSharedPreferenceInt(Context context, String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getInt(key, defValue);
    }
}
