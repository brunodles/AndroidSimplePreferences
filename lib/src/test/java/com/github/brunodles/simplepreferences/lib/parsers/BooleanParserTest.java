package com.github.brunodles.simplepreferences.lib.parsers;

import android.content.SharedPreferences;
import android.test.suitebuilder.annotation.SmallTest;

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
public class BooleanParserTest {

    public static class WhenHaveSavedValue {
        private BooleanParser booleanParser;
        private SharedPreferences preferences;
        private SampleObject object;
        private Field field;

        @Before
        public void setup() throws NoSuchFieldException {
            booleanParser = new BooleanParser();
            preferences = mock(SharedPreferences.class);
            when(preferences.getBoolean("field", false)).thenReturn(true);
            object = new SampleObject();
            field = SampleObject.class.getField("field");
            field.setAccessible(true);
        }

        @Test
        @SmallTest
        public void shouldReturnTheSavedValue() throws IllegalAccessException {
            booleanParser.load(preferences, field, object, field.getAnnotation(Property.class));
            assertEquals("Should be equals", true, object.field);
            verify(preferences, times(1)).getBoolean("field", false);
        }

        private static class SampleObject {
            @Property public boolean field;
        }
    }

    public static class WhenDontHaveSavedValueButHaveDefault {
        private BooleanParser stringParser;
        private SharedPreferences preferences;
        private SampleObject object;
        private Field field;

        @Before
        public void setup() throws NoSuchFieldException {
            stringParser = new BooleanParser();
            preferences = mock(SharedPreferences.class);
            when(preferences.getBoolean("field", true)).thenReturn(true);
            object = new SampleObject();
            field = SampleObject.class.getField("field");
            field.setAccessible(true);
        }

        @Test
        @SmallTest
        public void shouldReturnTheDefaultValue() throws IllegalAccessException {
            stringParser.load(preferences, field, object, field.getAnnotation(Property.class));
            assertEquals(true, object.field);
            verify(preferences, times(1)).getBoolean("field", true);
        }

        private static class SampleObject {
            @Property public boolean field = true;
        }
    }
}