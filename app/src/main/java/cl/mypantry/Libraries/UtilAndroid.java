package cl.mypantry.Libraries;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UtilAndroid {
    public static Intent redirect(Activity activity, Class<?> view) {
        Intent intent = new Intent(activity, view);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        return intent;
    }

    public static long getDifferenceDays(Date currentDate, Date compareDate) {
        long diff = compareDate.getTime() - currentDate.getTime();

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
