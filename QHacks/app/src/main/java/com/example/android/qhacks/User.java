package com.example.android.qhacks;

/**
 * Created by Gilbert Lam on 2017-02-03.
 */

public abstract class User {
    protected String name;
    protected String phoneNumber;
    protected String country;
    protected String province;
    protected String age;
    protected boolean isDoctor;
    public User(){

    }
    public boolean getIsDoctor(){
        return isDoctor;
    }

    public String getName() {
        return name;
    }

    public String getAge(){
        return age;
    }
    public String getPhoneNumber() {return phoneNumber;}
    public String getCountry(){
        return country;
    }
    public String getProvince(){
        return province;
    }
}
