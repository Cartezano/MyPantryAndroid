package cl.mypantry.Libraries;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Map;

public class UtilAndroid {
    public static Intent redirect(Activity activity, Class<?> view) {
        Intent intent = new Intent(activity, view);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        return intent;
    }
}
