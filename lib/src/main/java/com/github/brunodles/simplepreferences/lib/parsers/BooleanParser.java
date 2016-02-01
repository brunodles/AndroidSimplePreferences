package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Parser;
import com.github.brunodles.simplepreferences.lib.Property;

import java.lang.reflect.Field;

import static com.github.brunodles.simplepreferences.lib.ParserHelper.getValue;
import static com.github.brunodles.simplepreferences.lib.ParserHelper.resolveKey;

public class BooleanParser implements Parser {

    @Override
    public boolean canResolve(Class<?> fieldType) {
        return fieldType.isAssignableFrom(Boolean.class) || fieldType.isAssignableFrom(Boolean.TYPE);
    }

    @Override
    public void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
        editor.putBoolean(resolveKey(field), getValue(field, object, false));
    }

    @Override
    public void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
        boolean defValue = getValue(field, object, false);
        field.set(object, preferences.getBoolean(resolveKey(field), defValue));
    }
}
