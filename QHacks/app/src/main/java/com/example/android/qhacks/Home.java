package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    //testing push
    public static Patient THIS_USER = new Patient("Gilbert", "18","6478878022","Canada","Ontario","Broken Arms");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ImageView homeButton = (ImageView) findViewById(R.id.homeImageView);

        final ImageView profileButton = (ImageView) findViewById(R.id.profileImageView);
        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // UserProfile.setUser(THIS_USER);
                Intent myIntent = new Intent(view.getContext(), UserProfile.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final ImageView searchButton = (ImageView) findViewById(R.id.searchImageView);
        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), search.class);
                startActivityForResult(myIntent, 0);
            }
        });




    }
}
