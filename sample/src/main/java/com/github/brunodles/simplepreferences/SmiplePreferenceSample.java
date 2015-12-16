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

public class SmiplePreferenceSample extends AppCompatActivity implements View.OnClickListener {

    private static List<Integer> DROIDS = Arrays.asList(R.id.droid1, R.id.droid2, R.id.droid3);

    private Button apply;
    private Button load;
    private CheckBox dark;
    private CheckBox light;
    private EditText characterName;
    private StarWarsPreferences preferences;
    private RadioGroup droid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smiple_preference);
        characterName = (EditText) findViewById(R.id.characterName);
        dark = (CheckBox) findViewById(R.id.dark);
        light = (CheckBox) findViewById(R.id.light);
        droid = (RadioGroup) findViewById(R.id.droid);
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
            this.droid.check(DROIDS.get(droidIndex));
        else
            droid.check(-1);
    }

    private void toPreference() {
        preferences.favoriteCharacter = characterName.getText().toString();
        preferences.dark = dark.isChecked();
        preferences.light = light.isChecked();
        int checkedRadioButtonId = droid.getCheckedRadioButtonId();
        if (checkedRadioButtonId >= 0)
            preferences.droid = DROIDS.indexOf(checkedRadioButtonId);
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
