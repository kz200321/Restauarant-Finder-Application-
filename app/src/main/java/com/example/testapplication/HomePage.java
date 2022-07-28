package com.example.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;

public class HomePage extends AppCompatActivity implements View.OnClickListener {
    private LocationManager locationManager;
    private TextView txtLongitude, txtLatitude;
    private User user;
    private Button btnFindRestaurant;
    protected double latitude, longitude;

    @Override
    public void onClick(View view) {
        startActivity(new Intent(HomePage.this, SearchPage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        txtLongitude = findViewById(R.id.txtLongitude);
        txtLatitude = findViewById(R.id.txtLatitude);
        btnFindRestaurant = findViewById(R.id.btnFindRestauarants);
        btnFindRestaurant.setOnClickListener(this);



    }


}