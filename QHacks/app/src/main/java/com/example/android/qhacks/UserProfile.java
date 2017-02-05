package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.api.GoogleApiClient;

public class UserProfile extends AppCompatActivity {//SELECT ID FROM user_info
    User currentUser = new Patient("Gilbert", "18","6478878022","Canada","Ontario","Broken Arms");;
    boolean isDoctor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        final ImageView homeButton = (ImageView) findViewById(R.id.homeImageView);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Home.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final ImageView profileButton = (ImageView) findViewById(R.id.profileImageView);

        final ImageView searchButton = (ImageView) findViewById(R.id.searchImageView);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), search.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final TextView userName = (TextView) findViewById(R.id.nameLabel);
        userName.setText(currentUser.getName());

        final TextView userPhone = (TextView) findViewById(R.id.userPhone);
        userPhone.setText(currentUser.getPhoneNumber());

        final TextView userLocation = (TextView) findViewById(R.id.userLocation);
        userLocation.setText(currentUser.getProvince() +"," +currentUser.getCountry());

        final TextView userEmail = (TextView) findViewById(R.id.userQualification);
        userEmail.setText(currentUser.getEmail());

    }


    public void createUserObject(){
       //STUFF
    }

}
