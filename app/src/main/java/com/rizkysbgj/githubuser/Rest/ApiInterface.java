package com.rizkysbgj.githubuser.Rest;

import com.rizkysbgj.githubuser.Classes.GetUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET("users")
    Call<GetUser> getItems(
            @Query("q") String user,
            @Query("page") int pageIndex
    );
}
