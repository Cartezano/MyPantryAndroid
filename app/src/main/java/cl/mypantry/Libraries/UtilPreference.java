package cl.mypantry.Libraries;

import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Switch;

public class UtilPreference {

    public static void setSharedPreferences(SharedPreferences preferences, Switch switchRemember, String email, String password) {
        if (switchRemember.isChecked()) {
            SharedPreferences.Editor preferenceEditor = preferences.edit();
            preferenceEditor.putString("email", email);
            preferenceEditor.apply();
        } else {
            removeSharedPreferences(preferences);
        }
    }

    public static void removeSharedPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor =  preferences.edit();
        editor.remove("email");
        editor.apply();
    }

    public static void getPreferencesIfExist(SharedPreferences preferences, EditText editTextEmail, Switch switchRemember) {
        String email = getPreferenceEmail(preferences);

        if (email != null) {
            editTextEmail.setText(email);
            switchRemember.setChecked(true);
        }
    }

    public static String getPreferenceEmail(SharedPreferences preferences) {
        return preferences.getString("email", null);
    }
}
