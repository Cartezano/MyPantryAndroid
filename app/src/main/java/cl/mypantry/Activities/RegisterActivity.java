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

import cl.mypantry.R;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextName, editTextLastName, editTextEmail, editTextPassword, editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Enabled go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Bind input.
        bindLayout();

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
            goToLogin();
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

    private void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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

