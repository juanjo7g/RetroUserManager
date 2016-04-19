package com.juan.retrousermanagerapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juan.retrousermanagerapp.services.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juan on 6/03/16.
 */
public class RestClient {
    private static final String BASE_URL = "http://192.168.1.5:7002";
    private static RestClient instance = null;

    private UserService userService;

    protected RestClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);

    }

    public UserService getUserService() {
        return userService;
    }

    public static RestClient getInstance(){
        if(instance == null) {
            instance = new RestClient();
        }
        return instance;
    }
}
