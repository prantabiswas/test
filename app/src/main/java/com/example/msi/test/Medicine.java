package com.example.msi.test;

/**
 * Created by msi on 15-Jan-18.
 */

public class Medicine {

    private String name;
    private String dose;
    private String day;

    public Medicine(String name, String dose, String day) {
        this.name = name;
        this.dose = dose;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public String getDose() {
        return dose;
    }

    public String getDay() {
        return day;
    }
}
