package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

public class LoginPage extends AppCompatActivity {
    String[] login = new String[3];
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public String[] profileInfo = new String[1];
    private String userName, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //this.setContentView(R.layout.activity_login_page);
        setContentView(R.layout.activity_login_page);
        final EditText userNameBox = (EditText) findViewById(R.id.userName);
        final EditText passwordBox = (EditText) findViewById(R.id.password);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final ConnectToDB cTDB = new ConnectToDB();
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view) {
                userName = userNameBox.getText().toString();
                password = passwordBox.getText().toString();
                if(verifyLogin(userName, password)){
                    try {
                        profileInfo = cTDB.execute(login[2],"4").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    System.out.println(profileInfo[0]);
                    Intent myIntent = new Intent(view.getContext(), Home.class);
                    startActivityForResult(myIntent, 0);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid Login", Toast.LENGTH_LONG).show();
                }
            }
        });

      //  TextView tv=(TextView)findViewById(R.id.custom);
//        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Verdana.ttf");
        //tv.setTypeface(face);

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
        ConnectToDB cTDB1 = new ConnectToDB();
        //int count = 1;

        try {
            login = cTDB1.execute(userName, hashedPassword, "1").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (userName.equals(login[0]) && hashedPassword.equals(login[1])) {
            return true;
        }

        return false;
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

