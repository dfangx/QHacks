package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginPage extends AppCompatActivity {
    private String userName, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        new ConnectToDB().execute();
        final EditText userNameBox = (EditText) findViewById(R.id.userName);
        final EditText passwordBox = (EditText) findViewById(R.id.password);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view) {
                userName = userNameBox.getText().toString();
                password = passwordBox.getText().toString();
                if(verifyLogin(userName, password)){
                    Intent myIntent = new Intent(view.getContext(), Home.class);
                    startActivityForResult(myIntent, 0);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid Login", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Button registrationButton = (Button) findViewById(R.id.registerButton);
        registrationButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view) {
                Intent myIntent = new Intent(view.getContext(), Registration.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    public boolean verifyLogin(String userName, String password){
        String hashedPassword = hashing(password);
        return false;
        //^TESTING PURPOSES
        //Take the above strings and compare it with what you will have stored in the database
        //Return true if success, return false if not
    }
    public String hashing(String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
