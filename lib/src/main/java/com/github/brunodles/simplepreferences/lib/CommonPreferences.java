package com.github.brunodles.simplepreferences.lib;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.parsers.BooleanParser;
import com.github.brunodles.simplepreferences.lib.parsers.FloatParser;
import com.github.brunodles.simplepreferences.lib.parsers.IntegerParser;
import com.github.brunodles.simplepreferences.lib.parsers.LongParser;
import com.github.brunodles.simplepreferences.lib.parsers.StringParser;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

class CommonPreferences {

    private static List<Parser> parserList = Arrays.asList(
            new IntegerParser(),
            new LongParser(),
            new BooleanParser(),
            new FloatParser(),
            new StringParser());

    static void load(SharedPreferences preferences, Object object) {
        try {
            for (Field field : object.getClass().getDeclaredFields())
                if (field.isAnnotationPresent(Property.class))
                    loadField(preferences, field, object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void loadField(SharedPreferences preferences, Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        Class<?> fieldType = field.getType();
        for (Parser parser : parserList)
            if (parser.canResolve(fieldType)) {
                try {
                    parser.load(preferences, field, object, field.getAnnotation(Property.class));
                } catch (IllegalAccessException e) {
                }
                return;
            }
    }

    static void apply(SharedPreferences preferences, Object object) {
        objectToPrefernceEditor(preferences, object)
                .apply();
    }

    static void commit(SharedPreferences preferences, Object object) {
        objectToPrefernceEditor(preferences, object)
                .commit();
    }

    private static SharedPreferences.Editor objectToPrefernceEditor(SharedPreferences preferences, Object object) {
        SharedPreferences.Editor editor = preferences.edit();
        try {
            for (Field field : object.getClass().getDeclaredFields())
                if (field.isAnnotationPresent(Property.class))
                    try {
                        saveField(editor, field, object);
                    } catch (IllegalAccessException | UnknownFieldTypeException e) {
                    }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return editor;
    }

    private static void saveField(SharedPreferences.Editor editor, Field field, Object object) throws IllegalAccessException, UnknownFieldTypeException {
        field.setAccessible(true);
        Class<?> fieldType = field.getType();
        for (Parser parser : parserList)
            if (parser.canResolve(fieldType)) {
                parser.save(editor, field, object, field.getAnnotation(Property.class));
                return;
            }
        throw new UnknownFieldTypeException();
    }


    static void clear(SharedPreferences preference) {
        preference.edit().clear().apply();
    }
}
