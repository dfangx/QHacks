package com.example.android.qhacks;

/**
 * Created byr vrukshavakees on 2017-02-04.
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
    }
    public String getName (){
        return name;
    }
    public String getPostalCode(){
        return postalCode;
    }
    public String getPhoneNumber() {return phoneNumber;}
    public String getCountry(){
        return country;
    }
    public String getProvince(){
        return province;
    }
    public String[] getDescription(){
        return description;
    }
}
