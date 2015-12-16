package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Parser;
import com.github.brunodles.simplepreferences.lib.Property;

import java.lang.reflect.Field;

/**
 * Created by bruno on 16/12/15.
 */
public class IntegerParser extends Parser {

    @Override
    public boolean canResolve(Class<?> fieldType) {
        return fieldType.isAssignableFrom(Integer.class) || fieldType.isAssignableFrom(Integer.TYPE);
    }

    @Override
    public void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
        editor.putInt(resolveKey(field), field.getInt(object));
    }

    @Override
    public void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
        String key = resolveKey(field);
        int defValue = field.getInt(object);
        int value = preferences.getInt(key, defValue);
        field.setInt(object, value);
    }
}