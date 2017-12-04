package cl.mypantry.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cl.mypantry.Libraries.UtilAndroid;
import cl.mypantry.Libraries.UtilPreference;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);

        if (UtilPreference.getPreferenceEmail(preferences) != null && UtilPreference.getPreferenceActive(preferences)) {
            intent = UtilAndroid.redirect(SplashActivity.this, PantryActivity.class);
        } else {
            intent = UtilAndroid.redirect(SplashActivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
