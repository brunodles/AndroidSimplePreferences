package com.github.brunodles.simplepreferences.lib;

import android.content.SharedPreferences;

import java.lang.reflect.Field;

public abstract class Parser {
    protected static String resolveKey(Field field) {
        String name = field.getAnnotation(Property.class).value();
        if ("".equalsIgnoreCase(name))
            name = field.getName();
        return name;
    }

    protected abstract boolean canResolve(Class<?> fieldType);

    public abstract void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException;

    public abstract void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException;
}
