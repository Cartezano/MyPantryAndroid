package cl.mypantry.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import cl.mypantry.API.API;
import cl.mypantry.API.ApiService.PantryService;
import cl.mypantry.Fragments.HomeFragment;
import cl.mypantry.Libraries.UtilAndroid;
import cl.mypantry.Libraries.UtilPreference;
import cl.mypantry.Models.Pantry;
import cl.mypantry.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPantryActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences preferences;

    private EditText editTextQuality;
    private DatePicker editTextExpirationDate;
    private PantryService service;
    private int intProductId;
    private Button buttonAddPantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pantry);

        // Enabled go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind input.
        bindLayout();

        service = API.setApi().create(PantryService.class);

        // Enabled share preferences
        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);

        // Set up the login form.
        buttonAddPantry.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int product_id = intProductId;
        int user_id = UtilPreference.getPreferenceId(preferences);
        int quality = Integer.parseInt(editTextQuality.getText().toString());
        Date expiration_date = new Date(editTextExpirationDate.getYear(), editTextExpirationDate.getMonth(), editTextExpirationDate.getDayOfMonth());

        createPantry(product_id, user_id, quality, expiration_date);

    }

    public void createPantry(int product_id, int user_id, int quality, Date expiration_date) {
        Pantry pantry = new Pantry(product_id, user_id, quality, expiration_date);
        service.createPantry(pantry).enqueue(new Callback<Pantry>() {
            @Override
            public void onResponse(Call<Pantry> call, Response<Pantry> response) {
                if(response.isSuccessful() && response.code() == 201){
                    Toast.makeText(AddPantryActivity.this, "", Toast.LENGTH_SHORT).show();

                    Intent intent = UtilAndroid.redirect(AddPantryActivity.this, HomeFragment.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddPantryActivity.this, getString(R.string.invalid_register), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Pantry> call, Throwable t) {
                Toast.makeText(AddPantryActivity.this, getString(R.string.error_register), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bindLayout() {
        intProductId = Integer.parseInt(getIntent().getStringExtra("product_id"));
        editTextQuality = (EditText) findViewById(R.id.quantity);
        editTextExpirationDate = (DatePicker) findViewById(R.id.expiration_date);
        buttonAddPantry = (Button) findViewById(R.id.add_pantry_button);
    }
}
