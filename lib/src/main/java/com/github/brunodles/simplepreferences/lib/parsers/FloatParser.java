package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Parser;
import com.github.brunodles.simplepreferences.lib.Property;

import java.lang.reflect.Field;

import static com.github.brunodles.simplepreferences.lib.ParserHelper.getValue;
import static com.github.brunodles.simplepreferences.lib.ParserHelper.resolveKey;

public class FloatParser implements Parser {

    @Override
    public boolean canResolve(Class<?> fieldType) {
        return fieldType.isAssignableFrom(Float.class) || fieldType.isAssignableFrom(Float.TYPE);
    }

    @Override
    public void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
        editor.putFloat(resolveKey(field), field.getFloat(object));
    }

    @Override
    public void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
        float defValue = getValue(field, object, 0f);
        float value = preferences.getFloat(resolveKey(field), defValue);
        field.set(object, value);
    }
}
