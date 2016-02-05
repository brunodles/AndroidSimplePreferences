package com.github.brunodles.simplepreferences.lib;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;

/**
 * Created by bruno on 05/02/16.
 */
public class ParserHelperTest {
    public static class WhenResolveKey {
        public static class WithCustomPropertyName {

            @Test
            @SmallTest
            public void shouldReturn_username() throws NoSuchFieldException {
                assertEquals("username", ParserHelper.resolveKey(User.class.getDeclaredField("name")));
            }

            public static class User {
                @Property("username") String name;
            }
        }

        public static class WithDefaultPropertyName {

            @Test
            @SmallTest
            public void shouldReturn_username() throws NoSuchFieldException {
                assertEquals("name", ParserHelper.resolveKey(User.class.getDeclaredField("name")));
            }

            public static class User {
                @Property() String name;
            }
        }
    }

    public static class WhenGetValue {

        public static class WithDefaultValue {
            private Goku goku;
            private Field powerField;

            @Before
            public void setup() throws NoSuchFieldException {
                goku = new Goku();
                powerField = Goku.class.getDeclaredField("power");
            }

            @Test
            @SmallTest
            public void ShouldReturn_9001() throws IllegalAccessException {
                assertEquals(9001f, ParserHelper.getValue(powerField, goku, 0f));
            }

            public static class Goku {
                @Property float power = 9001f;
            }
        }

        public static class WithoutDefaultValue {
            private Person regularPerson;
            private Field powerField;

            @Before
            public void setup() throws NoSuchFieldException {
                regularPerson = new Person();
                powerField = Person.class.getDeclaredField("power");
            }

            @Test
            @SmallTest
            public void ShouldReturn_0() throws IllegalAccessException {
                assertEquals(0f, ParserHelper.getValue(powerField, regularPerson, 0f));
            }

            public static class Person {
                @Property float power;
            }
        }
    }
}