package cl.mypantry.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import cl.mypantry.API.API;
import cl.mypantry.API.ApiResponse.UserModelResponse;
import cl.mypantry.API.ApiService.UserService;
import cl.mypantry.Libraries.UtilAndroid;
import cl.mypantry.R;
import cl.mypantry.Libraries.UtilPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;

    private Button buttonLogin;
    private EditText editTextEmail, editTextPassword;
    private Switch switchRemember;
    private UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Enabled go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind input.
        bindLayout();

        service = API.setApi().create(UserService.class);

        // Enabled share preferences
        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);

        UtilPreference.getPreferencesIfExist(preferences, editTextEmail, switchRemember);

        // Set up the login form.
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (login(email, password)) {
            loginUser(email, password);
        }
    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, getString(R.string.error_email_valid), Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, getString(R.string.error_password_valid), Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    public void loginUser(String email, String password) {
        service.loginUser(email, password).enqueue(new Callback<UserModelResponse>() {
            @Override
            public void onResponse(Call<UserModelResponse> call, Response<UserModelResponse> response) {
                if(response.isSuccessful() && response.code() == 200){
                    Toast.makeText(LoginActivity.this, getString(R.string.valid_login), Toast.LENGTH_LONG).show();
                    Intent intent = UtilAndroid.redirect(LoginActivity.this, PantryActivity.class);
                    startActivity(intent);
                    finish();

                    Log.d("Variable", "Variable user_id " + response.body().getUser().getId());
                    Log.d("Variable", "Variable user_id " + response.body().getUser().getEmail());
                    UtilPreference.setSharedPreferences(preferences, switchRemember, response.body().getUser().getId(), response.body().getUser().getEmail(), true);
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.invalid_login), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModelResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, getString(R.string.error_login), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    private void bindLayout() {
        editTextEmail = (EditText) findViewById(R.id.text_email);
        editTextPassword = (EditText) findViewById(R.id.text_password);
        switchRemember = (Switch) findViewById(R.id.switch_remember);
        buttonLogin = (Button) findViewById(R.id.button_login);
    }
}

