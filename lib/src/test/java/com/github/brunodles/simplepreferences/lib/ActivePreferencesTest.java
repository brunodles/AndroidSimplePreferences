package com.github.brunodles.simplepreferences.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by bruno on 05/02/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = Build.VERSION_CODES.JELLY_BEAN)
public class ActivePreferencesTest {

    private StarWarsPreferences pref;
    private Context context;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
        pref = new StarWarsPreferences(context);
    }

    @After
    public void tearDown() {
        pref.clear();
    }

    @Test
    @MediumTest
    public void checkDefaultValues() {
        assertEquals("Vader", pref.favoriteCharacter);
        assertFalse(pref.dark);
        assertTrue(pref.human);
        assertEquals(Integer.valueOf(2), pref.droid);
        assertEquals(0, pref.spaceship);
        assertEquals(0f, pref.power);
        assertEquals(70L, pref.age);
    }

    @Test
    @MediumTest
    public void checkSaveFunction() {
        pref.favoriteCharacter = "Dart Vader";
        pref.dark = true;
        pref.human = false;
        pref.droid = 1;
        pref.spaceship = 2;
        pref.power = 12.4F;
        pref.age = 50L;
        pref.commit();

        SharedPreferences sp = context.getSharedPreferences(StarWarsPreferences.class.getName(),
                Context.MODE_PRIVATE);
        assertEquals(pref.favoriteCharacter, sp.getString("favoriteCharacter", null));
        assertTrue(sp.getBoolean("dark", false));
        assertFalse(sp.getBoolean("human", false));
        assertEquals(pref.droid.intValue(), sp.getInt("droid", 0));
        assertEquals(pref.power, sp.getFloat("power", 12.4f));
        assertEquals(pref.age, sp.getLong("age", 70L));
    }

    @Test
    @MediumTest
    public void checkLoadFunction() {
        SharedPreferences.Editor edit = context.getSharedPreferences(
                StarWarsPreferences.class.getName(), Context.MODE_PRIVATE).edit();
        edit.putString("favoriteCharacter", "Anakin Skywalker")
                .putBoolean("dark", false)
                .putBoolean("human", true)
                .putInt("droid", 1)
                .putInt("spaceship", 2)
                .putFloat("power", 15f)
                .putLong("age", 9)
                .commit();

        pref.reload();

        assertEquals("Anakin Skywalker", pref.favoriteCharacter);
        assertFalse(pref.dark);
        assertTrue(pref.human);
        assertEquals(Integer.valueOf(1), pref.droid);
        assertEquals(2, pref.spaceship);
        assertEquals(15f, pref.power);
        assertEquals(9L, pref.age);
    }

    public static class StarWarsPreferences extends ActivePreferences {

        @Property public String favoriteCharacter = "Vader";
        @Property public Boolean dark;
        @Property public boolean human = true;
        @Property public Integer droid = 2;
        @Property public int spaceship = 0;
        @Property public float power = 0f;
        @Property public long age = 70L;

        public StarWarsPreferences(Context context) {
            super(context);
        }
    }
}