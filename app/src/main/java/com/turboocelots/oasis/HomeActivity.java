package com.turboocelots.oasis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button logoutButton = (Button) findViewById(R.id.logout_id);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(HomeActivity.this, WelcomeActivity.class);
                startActivity(nextActivity);
                finish();
            }
        });
    }
}
