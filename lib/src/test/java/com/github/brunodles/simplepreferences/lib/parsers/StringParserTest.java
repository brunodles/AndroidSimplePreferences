package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Property;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by bruno on 16/12/15.
 */
public class StringParserTest {

    public static class WhenHaveSavedValue {
        private StringParser stringParser;
        private SharedPreferences preferences;
        private SampleObject object;
        private Field field;

        @Before
        public void setup() throws NoSuchFieldException {
            stringParser = new StringParser();
            preferences = mock(SharedPreferences.class);
            when(preferences.getString("field", null)).thenReturn("test");
            object = new SampleObject();
            field = SampleObject.class.getField("field");
        }

        @Test
        public void shouldDeserialize() throws IllegalAccessException {
            stringParser.load(preferences, field, object, field.getAnnotation(Property.class));
            Assert.assertEquals("Should be equals", "test", object.field);
        }

        private static class SampleObject {
            @Property public String field;
        }
    }

    public static class WhenDontHaveSavedValueButHaveDefault {
        private StringParser stringParser;
        private SharedPreferences preferences;
        private SampleObject object;
        private Field field;

        @Before
        public void setup() throws NoSuchFieldException {
            stringParser = new StringParser();
            preferences = mock(SharedPreferences.class);
            when(preferences.getString("field", "defaultValue")).thenReturn("defaultValue");
            object = new SampleObject();
            field = SampleObject.class.getField("field");
        }

        @Test
        public void shouldDeserialize() throws IllegalAccessException {
            stringParser.load(preferences, field, object, field.getAnnotation(Property.class));
            Assert.assertEquals("Should be equals", "defaultValue", object.field);
        }

        private static class SampleObject {
            @Property public String field = "defaultValue";
        }
    }
}