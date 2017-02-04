package com.example.android.qhacks;

/**
 * Created by Gilbert Lam on 2017-02-03.
 */

public class Doctor extends User {
    private String[] qualifications;
    private String fieldOfStudy;
    public Doctor (String name, String postalCode, String fieldOfStudy, String phoneNumber, String country, String province, String[] qualifications) {
        this.name = name;
        this.postalCode = postalCode;
        this.fieldOfStudy = fieldOfStudy;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.province = province;
        this.qualifications = qualifications;
        isDoctor = true;
    }
    public String getFieldOfStudy(){return fieldOfStudy;}
    public String[] getQualifications(){
        return qualifications;
    }
}
