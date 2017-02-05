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
        System.out.println(qualifications+ " "+name +" " + " "+age +" " +phoneNumber+" "+country+" " +province+ " hey");
        System.out.println(qualifications.getClass());
        System.out.println(search.getClass());
            if (qualifications.contains(search) || search.equals(phoneNumber) || search.equals(country) || search.equals(name) || search.equals(province) || search.equals(age) || search.equals(email))
            return true;
        return false;
                /*if (search.equals(phoneNumber))
                    System.out.println("a");
                    if(country.equals(search))
                        System.out.println("a");
                        if(province.equals(search))
                            System.out.println("a");
                            if(age.equals(search))
                                System.out.println("a");
                                if(email.equals(search))
                                    return true;*/

       // System.out.println(name + " hi");return false;
    }

}
