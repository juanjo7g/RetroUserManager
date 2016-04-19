package com.juan.retrousermanagerapp.services;

import com.juan.retrousermanagerapp.model.MyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by juan on 6/03/16.
 */
public interface UserService {

    @GET("/api/user")
    Call<MyResponse> getUsers();

    @GET("/api/user/{id}")
    Call<MyResponse> getUser(@Path("id") String _id);
}
