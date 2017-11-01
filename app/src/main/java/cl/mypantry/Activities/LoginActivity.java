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

        // Enabled share preferences
        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);

        // Set up the login form.
        bindLayout();
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(login(email, password)) {
            goToPantry();
            setPreferences(email, password);
        }
    }

    private boolean login(String email, String password) {
        if(!isValidEmail(email)){
            Toast.makeText(this, getString(R.string.error_email_valid), Toast.LENGTH_LONG).show();
            return false;
        } else if(!isValidPassword(password)){
            Toast.makeText(this, getString(R.string.error_password_valid), Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void setPreferences(String email, String password){
        if(switchRemember.isChecked()){
            SharedPreferences.Editor preferenceEditor = preferences.edit();
            preferenceEditor.putString("email", email);
            preferenceEditor.putString("password", password);
            preferenceEditor.apply();
        }
    }

    private void setPreferencesIfExist(){
        String email = getEmailPreferences();
        if(email != null){
            editTextEmail.setText(email);
        }
    }

    private String getEmailPreferences(){
        return preferences.getString("email", null);
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        return password.length() >= 6;
    }

    private void goToPantry(){
        Intent intent = new Intent(this, PantryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void bindLayout(){
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }
}

