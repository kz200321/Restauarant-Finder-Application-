package com.example.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class SearchPage extends AppCompatActivity implements LocationListener{

    private LocationManager locationManager;
    private double latitude, longitude;
    private static final String key = "SVQXChKTZ8OxyB8AzqRHRdncVYkWJZ06hHpFBPoNHF59MfW3cVXXHC0aHoPQXrAbgiKc0CKIk1K-foh9Wmm2wtyeCOjT9Z2RU-p239BHddDqJH2dUq2LkeKCorveYnYx";
    private static final String baseURL = "https://api.yelp.com/v3/";
    private TextView textResults;
    private List<Business> restaurants;
    private RecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        recyclerView = findViewById(R.id.rvRestaurants);

        if (ActivityCompat.checkSelfPermission(SearchPage.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SearchPage.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 15, this);

        restaurants = new ArrayList<>();

        BusinessAdapter adapter = new BusinessAdapter(this, restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.yelp.com/v3/businesses/search?term=restaurants&latitude=37.786882&longitude=-122.399972")
                //.method("GET", body)
                .addHeader("Authorization", "Bearer SVQXChKTZ8OxyB8AzqRHRdncVYkWJZ06hHpFBPoNHF59MfW3cVXXHC0aHoPQXrAbgiKc0CKIk1K-foh9Wmm2wtyeCOjT9Z2RU-p239BHddDqJH2dUq2LkeKCorveYnYx")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("SearchPage", "onFaliure" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("SearchPage", "onResponse" + response);
                if(response.isSuccessful()){
                    String jsonString = response.body().string();
                    if(jsonString == null){
                        Log.w("SearchPage", "Did not receive valid response body from Yelp API... exiting");
                                return;
                    }

                    Gson gson = new Gson();
                    Type listRestaurants = new TypeToken<ArrayList<Business>>(){}.getType();
                    restaurants = gson.fromJson(jsonString, listRestaurants);
                    adapter.notifyDataSetChanged();

                    SearchPage.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textResults.setText(jsonString);
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();



//        txtLatitude.setText(String.valueOf(latitude));
//        txtLongitude.setText(String.valueOf(longitude));

        //Remove constant updates gets the current
        //locationManager.removeUpdates(this);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

}