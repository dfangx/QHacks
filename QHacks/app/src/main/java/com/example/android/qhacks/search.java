package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class search extends AppCompatActivity {
    User[] listOfUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final ImageView homeButton = (ImageView) findViewById(R.id.homeImageView);
        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Home.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final ImageView profileButton = (ImageView) findViewById(R.id.profileImageView);
        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), UserProfile.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final Button searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectToDB cTDB = new ConnectToDB();
                String logListArray[] = new String[cTDB.arrayLength];
                try {
                    logListArray = cTDB.execute("3").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                listOfUsers = new User[logListArray.length];
                String[] variables = new String[8];
                for (int i = 0; i < logListArray.length; i++)
                    if (logListArray[i].charAt(logListArray[i].length())==('0')) {
                        variables = logListArray[i].split("|");
                        listOfUsers[i] = new Patient(variables[0], variables[5],variables[2], variables[3], variables[4], variables[6]);
                    }
                    else
                        listOfUsers[i] = new Doctor(variables[0],variables[5],variables[1], variables[2], variables[3], variables[4], variables[6]);

                System.out.println("done done");
            }
        });



    }

    public String getKeyword(){
        EditText eT = (EditText)findViewById(R.id.searchBar);
        return eT.toString();
    }
}
