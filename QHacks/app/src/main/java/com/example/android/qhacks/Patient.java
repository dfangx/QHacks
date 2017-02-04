package com.example.android.qhacks;

/**
 * Created byr1 vrukshavakees on 2017-02-04.
 */

public class Patient extends User {
    private String[] description;
    public Patient (String name, String postalCode,String phoneNumber, String country, String province, String[] description) {
        this.name = name;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.province = province;
        this.description = description;
        isDoctor = false;
    }

    public String[] getDescription(){
        return description;
    }
}
