package com.example.android.qhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EnterInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_information);
        final EditText country = (EditText) findViewById(R.id.countryBox);
        final EditText phoneNumber = (EditText) findViewById(R.id.phoneBox);
        final EditText age = (EditText) findViewById(R.id.ageBox);
        final EditText province = (EditText) findViewById(R.id.provinceBox);
        final EditText description = (EditText) findViewById(R.id.descriptionBox);
        final RadioButton isDoctor = (RadioButton) findViewById(R.id.isDoctor);
        final Button doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isDoctor.isChecked())
                    new ConnectToDB().execute(country.getText().toString(), phoneNumber.getText().toString(), age.getText().toString(), province.getText().toString(), description.getText().toString(), "0", "2");
                else
                    new ConnectToDB().execute(country.getText().toString(), phoneNumber.getText().toString(), age.getText().toString(), province.getText().toString(), description.getText().toString(), "1", "2");
                Intent myIntent = new Intent(view.getContext(), Home.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
