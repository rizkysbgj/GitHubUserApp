package com.rizkysbgj.githubuser.Classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetUser {
    @SerializedName("items")
    ArrayList<User> items;

    public ArrayList<User> getItems() {
        return items;
    }
}
