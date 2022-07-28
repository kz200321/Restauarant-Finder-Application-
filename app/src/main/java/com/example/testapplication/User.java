package com.example.testapplication;

import android.location.Location;

public class User {

    private String name, age, email, ID;
    private double latitude, longitude;

    public User(String name, String age, String email, String ID){
        this.name = name;
        this.age = age;
        this.email = email;
        this.ID = ID;
    }

    public User(String name, String age, String email, String ID, double latitude, double longitude){
        this.name = name;
        this.age = age;
        this.email = email;
        this.ID = ID;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getID(){
        return this.ID;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public double getLongitude(){
        return this.longitude;
    }


    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof User)){
            return false;
        }

        User user = (User) o;

        if(name.compareTo(user.name) == 0){
            return true;
        }
        else if(age.compareTo(user.age) == 0){
            return true;
        }
        else if(email.compareTo(user.email) == 0){
            return true;
        }
        else if(ID.compareTo(user.ID) == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "The user's info is " + name + " " + age + " " + email + " " + ID + " " + latitude + " " + longitude;
    }

}
