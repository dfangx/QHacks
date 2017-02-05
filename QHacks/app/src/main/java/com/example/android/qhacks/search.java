package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class search extends AppCompatActivity {
    User[] listOfUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText searchBar = (EditText) findViewById(R.id.searchBar);
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
                    if (logListArray[i].charAt(logListArray[i].length()-1)==('0')) {
                        variables = logListArray[i].split("|");
                        listOfUsers[i] = new Patient(variables[0], variables[5],variables[2], variables[3], variables[4], variables[6]);
                    }
                    else
                        listOfUsers[i] = new Doctor(variables[0],variables[5],variables[1], variables[2], variables[3], variables[4], variables[6]);
                String key = searchBar.getText().toString();
                int i = 0;
                for (int x = 0; x < listOfUsers.length;x++){
                    if(listOfUsers[x].search(key)){
                        if(i == 0) {
                            final TextView firstCustomer = (TextView) findViewById(R.id.firstCustomer);
                            firstCustomer.setText(listOfUsers[x].getName());
                            final int index = x;
                            firstCustomer.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View view){
                                    UserProfile.currentUser = listOfUsers[index];
                                    Intent myIntent = new Intent(view.getContext(), UserProfile.class);
                                    startActivityForResult(myIntent, 0);
                                }
                            });
                        }
                        if(i == 1) {
                            final TextView secondCustomer = (TextView) findViewById(R.id.secondCustomer);
                            secondCustomer.setText(listOfUsers[x].getName());
                            final int index = x;
                            secondCustomer.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View view){
                                    UserProfile.currentUser = listOfUsers[index];
                                    Intent myIntent = new Intent(view.getContext(), UserProfile.class);
                                    startActivityForResult(myIntent, 0);
                                }
                            });
                        }
                        if(i == 2) {
                            final TextView thirdCustomer = (TextView) findViewById(R.id.thirdCustomer);
                            thirdCustomer.setText(listOfUsers[x].getName());
                            final int index = x;
                            thirdCustomer.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View view){
                                    UserProfile.currentUser = listOfUsers[index];
                                    Intent myIntent = new Intent(view.getContext(), UserProfile.class);
                                    startActivityForResult(myIntent, 0);
                                }
                            });
                        }
                        if(i == 3) {
                            final TextView fourthCustomer = (TextView) findViewById(R.id.fourthCustomer);
                            fourthCustomer.setText(listOfUsers[x].getName());
                            final int index = x;
                            fourthCustomer.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View view){
                                    UserProfile.currentUser = listOfUsers[index];
                                    Intent myIntent = new Intent(view.getContext(), UserProfile.class);
                                    startActivityForResult(myIntent, 0);
                                }
                            });
                        }
                        if(i == 4) {
                            final TextView fifthCustomer = (TextView) findViewById(R.id.fifthCustomer);
                            fifthCustomer.setText(listOfUsers[x].getName());
                            final int index = x;
                            fifthCustomer.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View view){
                                    UserProfile.currentUser = listOfUsers[index];
                                    Intent myIntent = new Intent(view.getContext(), UserProfile.class);
                                    startActivityForResult(myIntent, 0);
                                }
                            });
                            break;
                        }
                        i++;
                    }
                }
            }
        });



    }

    public String getKeyword(){
        EditText eT = (EditText)findViewById(R.id.searchBar);
        return eT.toString();
    }
}
