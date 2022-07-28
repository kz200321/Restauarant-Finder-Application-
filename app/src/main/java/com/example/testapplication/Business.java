package com.example.testapplication;


import com.google.gson.annotations.SerializedName;

public class Business {

    @SerializedName("name")  private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
