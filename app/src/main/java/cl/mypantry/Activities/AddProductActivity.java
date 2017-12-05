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

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cl.mypantry.API.API;
import cl.mypantry.API.ApiResponse.PantryModelResponse;
import cl.mypantry.API.ApiResponse.ProductModelResponse;
import cl.mypantry.API.ApiService.PantryService;
import cl.mypantry.API.ApiService.ProductService;
import cl.mypantry.Libraries.UtilAndroid;
import cl.mypantry.Libraries.UtilPreference;
import cl.mypantry.Models.Pantry;
import cl.mypantry.Models.Product;
import cl.mypantry.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences preferences;

    private EditText editTextName, editTextBrand, editTextQuality;
    private DatePicker editTextExpirationDate;
    private PantryService pantryService;
    private ProductService productService;
    private BigInteger barcode;
    private Button buttonAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Enabled go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind input.
        bindLayout();

        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);

        pantryService = API.setApi().create(PantryService.class);
        productService = API.setApi().create(ProductService.class);

        // Set up the login form.
        buttonAddProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(editTextExpirationDate.getYear(), editTextExpirationDate.getMonth(), editTextExpirationDate.getDayOfMonth());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();
        Date expirationDate = calendar.getTime();

        BigInteger code = barcode;
        String name = editTextName.getText().toString();
        String brand = editTextBrand.getText().toString();
        int quality = Integer.parseInt(editTextQuality.getText().toString());
        int category_id = 1;
        int user_id = UtilPreference.getPreferenceId(preferences);
        String expiration_date = dateFormat.format(calendar.getTime());
        long dear_date = UtilAndroid.getDifferenceDays(currentDate, expirationDate);

        createProduct(code, name, brand, dear_date, category_id, user_id, quality, expiration_date);
    }

    public void createProduct(BigInteger code, String name, String brand, long dear_date, int category_id, final int user_id, final int quality, final String expiration_date) {
        final Product product = new Product(code, name, brand, dear_date, category_id);

        productService.createProduct(product).enqueue(new Callback<ProductModelResponse>() {
            @Override
            public void onResponse(Call<ProductModelResponse> call, Response<ProductModelResponse> response) {
                if(response.isSuccessful() && response.code() == 201){
                    Toast.makeText(AddProductActivity.this, "Se ha creado el producto.", Toast.LENGTH_SHORT).show();
                    Log.d("Variable", "Product id: " + response.body().getProduct().getId());
                    createPantry(user_id, response.body().getProduct().getId(), quality, expiration_date);
                }
            }

            @Override
            public void onFailure(Call<ProductModelResponse> call, Throwable t) {

            }
        });
    }

    public void createPantry(int user_id, int product_id, int quality, String expiration_date) {
        Pantry pantry = new Pantry(user_id, product_id, quality, expiration_date);

        pantryService.addProduct(pantry).enqueue(new Callback<PantryModelResponse>() {
            @Override
            public void onResponse(Call<PantryModelResponse> call, Response<PantryModelResponse> response) {
                if(response.isSuccessful() && response.code() == 201){
                    Toast.makeText(AddProductActivity.this, "Se ha agregado a su despensa.", Toast.LENGTH_SHORT).show();
                    Intent intent = UtilAndroid.redirect(AddProductActivity.this, PantryActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PantryModelResponse> call, Throwable t) {

            }
        });
    }

    private void bindLayout() {
        barcode = new BigInteger(getIntent().getStringExtra("barcode"));
        editTextName = (EditText) findViewById(R.id.product_name);
        editTextBrand = (EditText) findViewById(R.id.brand);
        editTextQuality = (EditText) findViewById(R.id.quantity);
        editTextExpirationDate = (DatePicker) findViewById(R.id.expiration_date);
        buttonAddProduct = (Button) findViewById(R.id.add_product_button);
    }
}
