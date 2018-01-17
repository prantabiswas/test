package com.example.msi.test;

import java.util.ArrayList;

/**
 * Created by msi on 15-Jan-18.
 */

public class Makeprescription {
    private String PrescriptionId;
    private String Problem_name;
    private String date;
    private String BP;
    private String weight;
    private String Doctor_name;
    private String Advice;
    private ArrayList<Medicine> medicines;

    public  Makeprescription(){}

    public Makeprescription(String prescriptionId, String problem_name, String date, String BP, String weight, String doctor_name, String advice,ArrayList Medicines) {
        PrescriptionId = prescriptionId;
        Problem_name = problem_name;
        this.date = date;
        this.BP = BP;
        this.weight = weight;
        Doctor_name = doctor_name;
        Advice = advice;
        this.medicines=Medicines;
    }

    public String getPrescriptionId() {
        return PrescriptionId;
    }

    public String getProblem_name() {
        return Problem_name;
    }

    public String getDate() {
        return date;
    }

    public String getBP() {
        return BP;
    }

    public String getWeight() {
        return weight;
    }

    public String getDoctor_name() {
        return Doctor_name;
    }

    public String getAdvice() {
        return Advice;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }
}
