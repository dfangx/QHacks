package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class search extends AppCompatActivity {

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

        final ImageView searchButton = (ImageView) findViewById(R.id.searchImageView);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = getKeyword();
                ConnectToDB cTDB = new ConnectToDB();
                cTDB.execute("3");
                String logListArray[] = new String[cTDB.arrayLength];
                for (int i = 0; i < logListArray.length; i++)
                    System.out.println(logListArray[i]);
            }
        });



    }

    public String getKeyword(){
        EditText eT = (EditText)findViewById(R.id.searchBar);
        return eT.toString();
    }
}
