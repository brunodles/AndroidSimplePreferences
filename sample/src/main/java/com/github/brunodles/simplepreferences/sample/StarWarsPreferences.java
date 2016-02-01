package com.github.brunodles.simplepreferences.sample;

import android.content.Context;

import com.github.brunodles.simplepreferences.lib.ActivePreferences;
import com.github.brunodles.simplepreferences.lib.Property;

/**
 * Created by bruno on 16/12/15.
 */
public class StarWarsPreferences extends ActivePreferences {

    @Property public String favoriteCharacter = "Vader";
    @Property public Boolean dark;
    @Property public boolean light;
    @Property public Integer droid = 2;
    @Property public int spaceship = 0;

    public StarWarsPreferences(Context context) {
        super(context);
    }
}
