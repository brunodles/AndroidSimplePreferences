package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Parser;
import com.github.brunodles.simplepreferences.lib.Property;

import java.lang.reflect.Field;

import static com.github.brunodles.simplepreferences.lib.ParserHelper.getValue;
import static com.github.brunodles.simplepreferences.lib.ParserHelper.resolveKey;

public class LongParser implements Parser {

    @Override
    public boolean canResolve(Class<?> fieldType) {
        return fieldType.isAssignableFrom(Long.class) || fieldType.isAssignableFrom(Long.TYPE);
    }

    @Override
    public void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
        editor.putLong(resolveKey(field), getValue(field, object, 0L));
    }

    @Override
    public void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
        long defValue = getValue(field, object, 0L);
        field.setLong(object, preferences.getLong(resolveKey(field), defValue));
    }
}
