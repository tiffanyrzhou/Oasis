package com.turboocelots.oasis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import com.turboocelots.oasis.R;

public class WelcomeActivity extends AppCompatActivity {
    /**
     * Instantiates the Welcome activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        final Button loginButton = (Button) findViewById(R.id.login_id);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(nextActivity);
            }
        });

        final Button registerButton = (Button) findViewById(R.id.register_id);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(nextActivity);
            }
        });
    }

}
