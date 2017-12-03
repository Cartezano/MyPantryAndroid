package cl.mypantry.Libraries;

import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Switch;

public class UtilPreference {

    public static void setSharedPreferences(SharedPreferences preferences, Switch switchRemember, int id, String email, boolean active) {
        if (switchRemember.isChecked()) {
            SharedPreferences.Editor preferenceEditor = preferences.edit();
            preferenceEditor.putInt("id", id);
            preferenceEditor.putString("email", email);
            preferenceEditor.putBoolean("active", active);
            preferenceEditor.apply();
        } else {
            removeSharedPreferences(preferences);
        }
    }

    public static void removeSharedPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor =  preferences.edit();
        editor.remove("id");
        editor.remove("email");
        editor.remove("active");
        editor.apply();
    }

    public static void removeActivePreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor =  preferences.edit();
        editor.remove("id");
        editor.remove("active");
        editor.apply();
    }

    public static void getPreferencesIfExist(SharedPreferences preferences, EditText editTextEmail, Switch switchRemember) {
        String email = getPreferenceEmail(preferences);

        if (email != null) {
            editTextEmail.setText(email);
            switchRemember.setChecked(true);
        }
    }

    public static int getPreferenceId(SharedPreferences preferences) {
        return preferences.getInt("id", 0);
    }

    public static String getPreferenceEmail(SharedPreferences preferences) {
        return preferences.getString("email", null);
    }

    public static boolean getPreferenceActive(SharedPreferences preferences) {
        return preferences.getBoolean("active", false);
    }
}
