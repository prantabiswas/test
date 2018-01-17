package com.example.msi.test;

/**
 * Created by msi on 14-Jan-18.
 */

public class Info {
    private String UserID;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String Number;

    public Info(){}

    public Info(String ID,String name, String dateOfBirth, String gender, String number) {
        this.UserID=ID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        Number = number;
    }

    public String getUserID() {
        return UserID;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getNumber() {
        return Number;
    }
}




