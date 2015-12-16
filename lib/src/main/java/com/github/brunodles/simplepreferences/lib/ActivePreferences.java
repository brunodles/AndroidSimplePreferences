package com.github.brunodles.simplepreferences.lib;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class ActivePreferences {

    private Context context;
    private String preferenceName;

    protected ActivePreferences(Context context) {
        this.context = context;
        preferenceName = this.getClass().getName();
        reload();
    }

    public void reload() {
        SharedPreferences preferences = getSharedPreferences();
        CommonPreferences.load(preferences, this);
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    protected ActivePreferences(Context context, String preferenceName) {
        this.context = context;
        this.preferenceName = preferenceName;
        reload();
    }

    public void apply() {
        CommonPreferences.apply(getSharedPreferences(), this);
    }

    public void clear() {
        CommonPreferences.clear(getSharedPreferences());
    }
}
