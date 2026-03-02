package com.example.profi26.model;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

public class SharedManger {
    private static SharedManger manager;
    private SharedPreferences prefs;

    private SharedManger(Context context) {
        prefs = context.getSharedPreferences("app_settings", MODE_PRIVATE);
    }

    public static synchronized SharedManger getInstance(Context context) {
        if (manager == null) {
            manager = new SharedManger(context);
        }

        return manager;
    }

    public boolean getOnBoarding1() {
        return prefs.getBoolean("onboarding1", false);
    }

    public boolean getOnBoarding2() {
        return prefs.getBoolean("onboarding2", false);
    }

    public boolean getOnBoarding3() {
        return prefs.getBoolean("onboarding3", false);
    }

    public void checkedOnBoarding1() {
        prefs.edit().putBoolean("onboarding1", true).apply();
    }

    public void checkedOnBoarding2() {
        prefs.edit().putBoolean("onboarding2", true).apply();
    }

    public void checkedOnBoarding3() {
        prefs.edit().putBoolean("onboarding3", true).apply();
    }

    public String getLanguage() {
        return prefs.getString("language", "Russian");
    }

    public void setLanguage(String country) {
        prefs.edit().putString("language", country).apply();
    }

}
