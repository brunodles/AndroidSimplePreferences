package com.github.brunodles.simplepreferences.lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class should be used to annotate your fields.
 * You can provide a custom name to be used to serialize this field, ortherwise the field name will
 * be used.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
    String value() default "";
}