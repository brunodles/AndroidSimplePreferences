package com.github.brunodles.simplepreferences.lib;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class is intented to act as a ActiveRecord. This means that it will store the data and
 * will be able to save / load itself.<br>
 * To use it will will need to create a new class that extend this.
 */
public abstract class ActivePreferences {

    private Context context;
    private String preferenceName;

    protected ActivePreferences(Context context) {
        this.context = context;
        preferenceName = this.getClass().getName();
        reload();
    }

    /**
     * Reload the data from the preferences file.
     */
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

    /**
     * Save current data to other objects.
     */
    public void apply() {
        CommonPreferences.apply(getSharedPreferences(), this);
    }

    /**
     * Clear the data file.
     */
    public void clear() {
        CommonPreferences.clear(getSharedPreferences());
    }
}
