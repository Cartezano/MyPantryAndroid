package cl.mypantry.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cl.mypantry.R;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.buttonLogin);
        signinButton = (Button) findViewById(R.id.buttonSignIn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(signin);
            }
        });
    }
}
