package com.github.brunodles.simplepreferences.lib;

import java.lang.reflect.Field;

/**
 * Created by bruno on 01/02/16.
 */
public final class ParserHelper {

    private ParserHelper() {
    }

    public static String resolveKey(Field field) {
        String name = field.getAnnotation(Property.class).value();
        if ("".equalsIgnoreCase(name))
            name = field.getName();
        return name;
    }

    public static <T> T getValue(Field field, Object object, T defaultValue) throws IllegalAccessException {
        Object o = field.get(object);
        if (o == null)
            return defaultValue;
        else
            return (T) o;
    }
}
