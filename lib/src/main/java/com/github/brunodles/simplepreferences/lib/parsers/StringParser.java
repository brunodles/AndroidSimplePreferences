package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Parser;
import com.github.brunodles.simplepreferences.lib.Property;

import java.lang.reflect.Field;

import static com.github.brunodles.simplepreferences.lib.ParserHelper.resolveKey;

public class StringParser implements Parser {

    @Override
    public boolean canResolve(Class<?> fieldType) {
        return fieldType.isAssignableFrom(String.class);
    }

    @Override
    public void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException {
        String key = resolveKey(field);
        String value = String.valueOf(field.get(object));
        editor.putString(key, value);
    }

    @Override
    public void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException {
        String key = resolveKey(field);
        Object currentValue = field.get(object);
        String defValue;
        if (currentValue == null)
            defValue = null;
        else
            defValue = String.valueOf(currentValue);
        String value = preferences.getString(key, defValue);
        field.set(object, value);
    }
}
