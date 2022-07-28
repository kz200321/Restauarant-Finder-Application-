package com.example.testapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YelpSearchResult {
    @SerializedName("total") private int total;
    @SerializedName("businesses") private List<Business> list;

}
