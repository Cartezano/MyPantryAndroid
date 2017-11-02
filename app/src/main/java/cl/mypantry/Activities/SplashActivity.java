package cl.mypantry.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cl.mypantry.Libraries.UtilPreference;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);

        Intent intentMain = new Intent(SplashActivity.this, MainActivity.class);
        Intent intentPantry = new Intent(SplashActivity.this, PantryActivity.class);

        if (UtilPreference.getPreferenceEmail(preferences) != null) {
            startActivity(intentPantry);
        } else {
            startActivity(intentMain);
        }
        finish();
    }
}
