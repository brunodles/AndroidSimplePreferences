package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Parser;
import com.github.brunodles.simplepreferences.lib.Property;

import java.lang.reflect.Field;

import static com.github.brunodles.simplepreferences.lib.ParserHelper.getValue;
import static com.github.brunodles.simplepreferences.lib.ParserHelper.resolveKey;

public class IntegerParser implements Parser {

    @Override
    public boolean canResolve(Class<?> fieldType) {
        return fieldType.isAssignableFrom(Integer.class) || fieldType.isAssignableFrom(Integer.TYPE);
    }

    @Override
    public void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
        editor.putInt(resolveKey(field), getValue(field, object, 0));
    }

    @Override
    public void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
        Integer defaultValue = getValue(field, object, 0);
        Integer value = preferences.getInt(resolveKey(field), defaultValue);
        field.set(object, value);
    }

}