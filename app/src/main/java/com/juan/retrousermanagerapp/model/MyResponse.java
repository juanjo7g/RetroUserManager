package com.juan.retrousermanagerapp.model;

import java.util.List;

/**
 * Created by juan on 6/03/16.
 */
public class MyResponse {

    private Number statusCode;
    private String message;
    private List<Object> data;

    public Number getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Number statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
