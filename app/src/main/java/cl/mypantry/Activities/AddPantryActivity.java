package cl.mypantry.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);

        service = API.setApi().create(PantryService.class);

        // Set up the login form.
        buttonAddPantry.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int product_id = intProductId;
        int user_id = UtilPreference.getPreferenceId(preferences);
        int quality = Integer.parseInt(editTextQuality.getText().toString());

        Calendar calendar = Calendar.getInstance();
        calendar.set(editTextExpirationDate.getYear(), editTextExpirationDate.getMonth(), editTextExpirationDate.getDayOfMonth());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expiration_date = dateFormat.format(calendar.getTime());

        createPantry(user_id, product_id, quality, expiration_date);

    }

    public void createPantry(int user_id, int product_id, int quality, String expiration_date) {
        Pantry pantry = new Pantry(user_id, product_id, quality, expiration_date);
        Log.d("Pantry", "Id de producto " + pantry.getProductId() + " ID del usuario " + pantry.getUserId() + " Cantidad: " + pantry.getQuality() + " fecha de expiración " + pantry.getExpirationDate());
        service.addProduct(pantry).enqueue(new Callback<Pantry>() {
            @Override
            public void onResponse(Call<Pantry> call, Response<Pantry> response) {
                if(response.isSuccessful() && response.code() == 200){
                    Toast.makeText(AddPantryActivity.this, "Se ha sumado a los que ya tenia.", Toast.LENGTH_SHORT).show();
                }
                if (response.isSuccessful() && response.code() == 201) {
                    Toast.makeText(AddPantryActivity.this, "Se ha agregado a su despensa.", Toast.LENGTH_SHORT).show();
                }
                Intent intent = UtilAndroid.redirect(AddPantryActivity.this, PantryActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Pantry> call, Throwable t) {
                Toast.makeText(AddPantryActivity.this, getString(R.string.error_register), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bindLayout() {
        intProductId = getIntent().getIntExtra("product_id", intProductId);
        editTextQuality = (EditText) findViewById(R.id.quantity);
        editTextExpirationDate = (DatePicker) findViewById(R.id.expiration_date);
        buttonAddPantry = (Button) findViewById(R.id.add_pantry_button);
    }
}
