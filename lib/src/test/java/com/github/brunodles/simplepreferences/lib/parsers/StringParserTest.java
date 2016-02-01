package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;

import com.github.brunodles.simplepreferences.lib.Property;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        public void shouldReturnTheSavedValue() throws IllegalAccessException {
            stringParser.load(preferences, field, object, field.getAnnotation(Property.class));
            assertEquals("Should be equals", "test", object.field);
            verify(preferences, times(1)).getString("field", null);
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
        public void shouldReturnTheDefaultValue3e() throws IllegalAccessException {
            stringParser.load(preferences, field, object, field.getAnnotation(Property.class));
            assertEquals("Should be equals", "defaultValue", object.field);
            verify(preferences, times(1)).getString("field", "defaultValue");
        }

        private static class SampleObject {
            @Property public String field = "defaultValue";
        }
    }
}