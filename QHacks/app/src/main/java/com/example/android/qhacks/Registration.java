package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final EditText name = (EditText) findViewById(R.id.nameBox);
        final EditText email = (EditText) findViewById(R.id.emailBox);
        final EditText password = (EditText) findViewById(R.id.passwordBox);
        final EditText userName = (EditText) findViewById(R.id.userNameBox);
        final EditText description = (EditText) findViewById(R.id.descriptionBox);
        final Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                new ConnectToDB().execute(name.getText().toString(), userName.getText().toString(), password.getText().toString(), email.getText().toString(), "0");
                Intent myIntent = new Intent(view.getContext(), EnterInformation.class);
                startActivityForResult(myIntent, 0);

            }
        });
    }
}
