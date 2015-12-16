package com.github.brunodles.simplepreferences.lib;

import android.content.Context;
import android.content.SharedPreferences;

public class DaoPreferences {

    Context context;

    public DaoPreferences(Context context) {
        this.context = context;
    }

    public <T> T load(T object, String name) {
        CommonPreferences.load(getSharedPreferences(name), object);
        return object;
    }

    private SharedPreferences getSharedPreferences(String preferenceName) {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    public <T> T save(T object, String name) {
        CommonPreferences.apply(getSharedPreferences(name), object);
        return object;
    }

    public void clear(String name) {
        CommonPreferences.clear(getSharedPreferences(name));
    }
}
