package cl.mypantry.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import cl.mypantry.API.API;
import cl.mypantry.API.ApiService.ProductService;
import cl.mypantry.R;

public class AddPantryActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences preferences;

    private EditText editTextQuality;
    private DatePicker editTextExpirationDate;
    private ProductService service;
    private Button buttonAddPantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pantry);

        // Enabled go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind input.
        bindLayout();

        service = API.setApi().create(ProductService.class);

        // Enabled share preferences
        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);

        // Set up the login form.
        buttonAddPantry.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    private void bindLayout() {
        editTextQuality = (EditText) findViewById(R.id.quantity);
        editTextExpirationDate = (DatePicker) findViewById(R.id.expiration_date);
        buttonAddPantry = (Button) findViewById(R.id.add_pantry_button);
    }
}
