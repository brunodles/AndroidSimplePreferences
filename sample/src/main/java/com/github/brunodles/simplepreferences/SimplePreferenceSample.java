package com.github.brunodles.simplepreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

public class SimplePreferenceSample extends AppCompatActivity implements View.OnClickListener {

    private static List<Integer> DROIDS = Arrays.asList(R.id.droid1, R.id.droid2, R.id.droid3);
    private static List<Integer> SPACESHIPS = Arrays.asList(R.id.spaceship1, R.id.spaceship2, R.id.spaceship3);

    private Button apply;
    private Button load;
    private CheckBox dark;
    private CheckBox light;
    private EditText characterName;
    private StarWarsPreferences preferences;
    private RadioGroup droid;
    private RadioGroup spaceship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smiple_preference);
        characterName = (EditText) findViewById(R.id.characterName);
        dark = (CheckBox) findViewById(R.id.dark);
        light = (CheckBox) findViewById(R.id.light);
        droid = (RadioGroup) findViewById(R.id.droid);
        spaceship = (RadioGroup) findViewById(R.id.spaceship);
        load = (Button) findViewById(R.id.load);
        apply = (Button) findViewById(R.id.apply);

        load.setOnClickListener(this);
        apply.setOnClickListener(this);

        preferences = new StarWarsPreferences(this);
        toView();
    }

    private void toView() {
        preferences.reload(); // you can even reload the preferences, if you changes something
        characterName.setText(preferences.favoriteCharacter);
        dark.setChecked(preferences.dark);
        light.setChecked(preferences.light);
        int droidIndex = preferences.droid;
        if (droidIndex >= 0)
            droid.check(DROIDS.get(droidIndex));
        else
            droid.check(-1);
        int shipIndex = preferences.spaceship;
        if (shipIndex >= 0)
            spaceship.check(SPACESHIPS.get(shipIndex));
        else
            spaceship.check(-1);
    }

    private void toPreference() {
        preferences.favoriteCharacter = characterName.getText().toString();
        preferences.dark = dark.isChecked();
        preferences.light = light.isChecked();
        int checkedRadioButtonId = droid.getCheckedRadioButtonId();
        if (checkedRadioButtonId >= 0)
            preferences.droid = DROIDS.indexOf(checkedRadioButtonId);
        int checkedSpaceShipId = spaceship.getCheckedRadioButtonId();
        if (checkedSpaceShipId >= 0)
            preferences.spaceship = SPACESHIPS.indexOf(checkedSpaceShipId);
        preferences.apply();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(load)) {
            toView();
        } else {
            toPreference();
        }
    }
}
