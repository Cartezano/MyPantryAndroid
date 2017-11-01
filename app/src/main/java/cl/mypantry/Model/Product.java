package cl.mypantry.Model;

import android.app.Activity;

import com.google.zxing.integration.android.IntentIntegrator;

/**
 * Created by hugo on 10/25/17.
 */

public class Product {
    private IntentIntegrator integrator;
    private Activity activity;

    public Product(){

    }

    public Product(IntentIntegrator intentIntegrator, Activity activity){
        this.integrator = intentIntegrator;
        this.activity = activity;
    }
}
