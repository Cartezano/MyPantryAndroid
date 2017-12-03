package cl.mypantry.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cl.mypantry.API.API;
import cl.mypantry.API.ApiService.UserService;
import cl.mypantry.Libraries.UtilAndroid;
import cl.mypantry.Models.User;
import cl.mypantry.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;

    private UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Enabled go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind input.
        bindLayout();

        service = API.setApi().create(UserService.class);

        // Set up the signin form.
        buttonSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        if(register(name, lastName, email, password, confirmPassword)){
            createUser(name, lastName, email, password, 1);
        }
    }

    private boolean register(String name, String lastName, String email, String password, String confirmPassword) {
        if (!isValidName(name)) {
            Toast.makeText(this, getString(R.string.error_name_valid), Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidLastName(lastName)) {
            Toast.makeText(this, getString(R.string.error_last_name_valid), Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidEmail(email)) {
            Toast.makeText(this, getString(R.string.error_email_valid), Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, getString(R.string.error_password_valid), Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(confirmPassword)) {
            Toast.makeText(this, getString(R.string.error_confirm_password_valid), Toast.LENGTH_LONG).show();
            return false;
        } else if (!isEqualPassword(password, confirmPassword)) {
            Toast.makeText(this, getString(R.string.error_password_equals), Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    public void createUser(String name, String lastName, String email, String password, int userType) {
        User user = new User(name, lastName, email, password, userType);
        service.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful() && response.code() == 201){
                    Toast.makeText(RegisterActivity.this, getString(R.string.valid_register), Toast.LENGTH_LONG).show();
                    Intent intent = UtilAndroid.redirect(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, getString(R.string.invalid_register), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, getString(R.string.error_register), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isValidName(String name) {
        return name.length() >= 2;
    }

    private boolean isValidLastName(String lastName) {
        return lastName.length() >= 2;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    private boolean isEqualPassword(String password, String confirmPassword) {
        if (confirmPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    private void bindLayout() {
        editTextName = (EditText) findViewById(R.id.text_name);
        editTextLastName = (EditText) findViewById(R.id.text_last_name);
        editTextEmail = (EditText) findViewById(R.id.text_email);
        editTextPassword = (EditText) findViewById(R.id.text_password);
        editTextConfirmPassword = (EditText) findViewById(R.id.text_confirm_password);
        buttonSignIn = (Button) findViewById(R.id.button_sign_in);
    }
}

