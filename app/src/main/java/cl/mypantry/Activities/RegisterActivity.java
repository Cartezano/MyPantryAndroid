package cl.mypantry.Activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cl.mypantry.R;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private View signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Enabled go back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the signin form.
        signinButton = (Button) findViewById(R.id.sign_in_button);
        signinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Sign in success.", Toast.LENGTH_LONG).show();

        Intent login = new Intent(view.getContext(), LoginActivity.class);
        startActivity(login);
    }
}

