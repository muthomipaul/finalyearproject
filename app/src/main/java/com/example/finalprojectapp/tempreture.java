package com.example.finalprojectapp;

public class tempreture {

    private String created_at;
    private String entry_ID;
    private String tempValue;

    public tempreture(){}

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEntry_ID() {
        return entry_ID;
    }

    public void setEntry_ID(String entry_ID) {
        this.entry_ID = entry_ID;
    }

    public String getTempValue() {
        return tempValue;
    }

    public void setTempValue(String tempValue) {
        this.tempValue = tempValue;
    }

    public tempreture(String created_at, String entry_ID, String tempValue){
        this.created_at= created_at;
        this.entry_ID= entry_ID;
        this.tempValue=tempValue;
    }
}
