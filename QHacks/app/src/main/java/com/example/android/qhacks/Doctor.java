package com.example.android.qhacks;

/**
 * Created by Gilbert Lam on 2017-02-03.
 */
//kk
public class Doctor extends User {
    private String qualifications;
    private String fieldOfStudy;
    public Doctor (String name, String age, String email, String phoneNumber, String country, String province, String qualifications) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.province = province;
        this.qualifications = qualifications;
        isDoctor = true;
    }
    public String getFieldOfStudy(){return fieldOfStudy;}
    public String getQualifications(){
        return qualifications;
    }
    public boolean search(String search){
        if (qualifications.contains(search) || name.equals(search) || phoneNumber.equals(search) || country.equals(search) || province.equals(search) || age.equals(search) || email.equals(search)){
            return true;
        }
        return false;
    }
}
