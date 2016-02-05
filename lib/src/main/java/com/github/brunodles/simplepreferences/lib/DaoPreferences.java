package com.github.brunodles.simplepreferences.lib;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class will work as a Data Access Object. With that will be able to pass objects and receive
 * loaded object. The point is that you don't need to extend any framework class to use it.
 */
public class DaoPreferences {

    Context context;

    /**
     * @param context we need the context to be able to save/load the preferences.
     */
    public DaoPreferences(Context context) {
        this.context = context;
    }

    /**
     * Fill a empty object with loaded data.
     *
     * @param object A empty object
     * @param key    The key was used to save the object
     * @param <T>    The object type will be detected
     * @return The same object filled with data.
     */
    public <T> T load(T object, String key) {
        CommonPreferences.load(getSharedPreferences(key), object);
        return object;
    }

    private SharedPreferences getSharedPreferences(String preferenceName) {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    /**
     * Save a object using that key as filename.
     * When you use this method, the data will be saved on a background thread.
     * You can take a look on {@link SharedPreferences.Editor#apply()}.
     *
     * @param object The object you want to save
     * @param key    The key used to retrieve the object later.
     * @param <T>    The object type will be detected
     * @return The saved Object
     */
    public <T> T apply(T object, String key) {
        CommonPreferences.apply(getSharedPreferences(key), object);
        return object;
    }

    /**
     * Save a object using that key as filename.
     * When you use this method, the data will be saved synchronously.
     * You can take a look on {@link SharedPreferences.Editor#commit()}.
     *
     * @param object The object you want to save
     * @param key    The key used to retrieve the object later.
     * @param <T>    The object type will be detected
     * @return The saved Object
     */
    public <T> T commit(T object, String key) {
        CommonPreferences.apply(getSharedPreferences(key), object);
        return object;
    }

    /**
     * Clear a key
     *
     * @param key The key to be cleared
     */
    public void clear(String key) {
        CommonPreferences.clear(getSharedPreferences(key));
    }
}
