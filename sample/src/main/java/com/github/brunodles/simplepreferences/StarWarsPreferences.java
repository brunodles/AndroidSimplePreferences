package com.github.brunodles.simplepreferences;

import android.content.Context;

import com.github.brunodles.simplepreferences.lib.Property;
import com.github.brunodles.simplepreferences.lib.ActivePreferences;

/**
 * Created by bruno on 16/12/15.
 */
public class StarWarsPreferences extends ActivePreferences {

    @Property public String favoriteCharacter="Vader";
    @Property public boolean dark;
    @Property public boolean light;
    @Property public int droid = -1;

    public StarWarsPreferences(Context context) {
        super(context);
    }
}
