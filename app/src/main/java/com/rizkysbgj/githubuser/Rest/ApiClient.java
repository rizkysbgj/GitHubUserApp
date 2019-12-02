package com.rizkysbgj.githubuser.Rest;

import com.rizkysbgj.githubuser.Classes.User;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.converter.gson.GsonConverterFactory.create;

public class ApiClient {
    public static final String BASE_URL = "https://api.github.com/search/";
    private static ApiInterface retrofit = null;

    private static ApiInterface getClient(Class<ApiInterface> user) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(create())
                    .build()
                    .create(user);
        }
        return retrofit;
    }

    public static ApiInterface user() {
        return getClient(ApiInterface.class);
    }
}
