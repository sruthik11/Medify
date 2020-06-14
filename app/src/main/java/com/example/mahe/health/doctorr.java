package com.example.mahe.health;

public class doctorr {
    String PName;
    String Age;
    String Name;
    String Diagnosis;
    String Prescription;

    public doctorr() {

    }

    @Override
    public String toString() {
        return "doctorr{" +
                "PName='" + PName + '\'' +
                ", Age='" + Age + '\'' +
                ", Name='" + Name + '\'' +
                ", Diagnosis='" + Diagnosis + '\'' +
                ", Prescription='" + Prescription + '\'' +
                '}';
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }

    public String getPrescription() {
        return Prescription;
    }

    public void setPrescription(String prescription) {
        Prescription = prescription;
    }
}