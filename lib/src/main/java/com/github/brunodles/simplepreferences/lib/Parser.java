package com.github.brunodles.simplepreferences.lib;

import android.content.SharedPreferences;

import java.lang.reflect.Field;

/**
 * This interface is used to parse objects
 */
public interface Parser {

    boolean canResolve(Class<?> fieldType);

    void save(SharedPreferences.Editor editor, Field field, Object object, Property annotation) throws IllegalAccessException;

    void load(SharedPreferences preferences, Field field, Object object, Property annotation) throws IllegalAccessException;
}
