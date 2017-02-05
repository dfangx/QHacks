package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(!name.getText().toString().equals("") || !email.getText().toString().equals("") || !password.getText().toString().equals("") || !userName.getText().toString().equals("")) {
                    new ConnectToDB().execute(name.getText().toString().toUpperCase(), userName.getText().toString().toUpperCase(), password.getText().toString(), email.getText().toString(), "0");
                    Intent myIntent = new Intent(view.getContext(), EnterInformation.class);
                    startActivityForResult(myIntent, 0);
                }
                else {
                    Toast.makeText(getApplicationContext(),"FILL ALL FIELDS", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
