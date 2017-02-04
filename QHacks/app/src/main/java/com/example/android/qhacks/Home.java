package com.example.android.qhacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    //testing push
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ImageView homeButton = (ImageView) findViewById(R.id.homeImageView);
        final ImageView profileButton = (ImageView) findViewById(R.id.profileImageView);
        final ImageView searchButton = (ImageView) findViewById(R.id.searchImageView);
    }
}
