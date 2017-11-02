package cl.mypantry.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import cl.mypantry.R;
import cl.mypantry.Libraries.UtilPreference;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;

    private Button buttonLogin;
    private EditText editTextEmail, editTextPassword;
    private Switch switchRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Enabled go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind input.
        bindLayout();

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
            goToPantry();
            UtilPreference.setSharedPreferences(preferences, switchRemember, email, password);
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

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    private void goToPantry() {
        Intent intent = new Intent(LoginActivity.this, PantryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void bindLayout() {
        editTextEmail = (EditText) findViewById(R.id.text_email);
        editTextPassword = (EditText) findViewById(R.id.text_password);
        switchRemember = (Switch) findViewById(R.id.switch_remember);
        buttonLogin = (Button) findViewById(R.id.button_login);
    }
}

