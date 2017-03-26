package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.User;

public class ViewAccountsActivity extends AppCompatActivity {
    private String selectedAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_accounts);
        final Button selectButton = (Button) findViewById(R.id.select_button);
        final Button cancelButton = (Button) findViewById(R.id.cancel_button);
        final ListView accountsList = (ListView) findViewById(R.id.accounts_listview);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);

        //Create listAdapter for ListView
        ArrayAdapter<User> accountsListAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1,
                Model.getInstance().getUsers());
        accountsList.setAdapter(accountsListAdapter);

        //Initializes Buttons
        selectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedAccount = ((User) accountsList.getSelectedItem()).getName();
                Intent nextActivity  = new Intent(ViewAccountsActivity.this, ManageAccountActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("SelectedAccount", selectedAccount);
                startActivity(nextActivity);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(ViewAccountsActivity.this, HomeActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });
    }
}
