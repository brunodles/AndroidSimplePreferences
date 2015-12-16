package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Parser;
import com.github.brunodles.simplepreferences.lib.Property;

import java.lang.reflect.Field;

public class BooleanParser extends Parser {

    @Override
    public boolean canResolve(Class<?> fieldType) {
        return fieldType.isAssignableFrom(Boolean.class) || fieldType.isAssignableFrom(Boolean.TYPE);
    }

    @Override
    public void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
        editor.putBoolean(resolveKey(field), field.getBoolean(object));
    }

    @Override
    public void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
        field.setBoolean(object, preferences.getBoolean(resolveKey(field), field.getBoolean(object)));
    }
}
