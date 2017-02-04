package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final ImageView homeButton = (ImageView) findViewById(R.id.homeImageView);
        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Home.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final ImageView profileButton = (ImageView) findViewById(R.id.profileImageView);

        final ImageView searchButton = (ImageView) findViewById(R.id.searchImageView);
        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), search.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
