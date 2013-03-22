package com.example.androidApp.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 9:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApiRequest {
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    private String method;
    private String url;
    private String username;
    private String password;
    private Map<String, String> headers = new HashMap<String, String>();

    public ApiRequest() {
        addHeader("Content-Type", "application/xml");
        addHeader("Accept", "application/xml");
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    protected void setMethod(String method) {
        this.method = method;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    protected void addHeader(String name, String value) {
        getHeaders().put(name, value);
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals (Object otherObject) {
        boolean isEqual = false;

        if (otherObject instanceof ApiRequest) {
            ApiRequest otherRequest = (ApiRequest)otherObject;
            isEqual = true;

            isEqual = isEqual && getMethod().equals(otherRequest.getMethod());
            isEqual = isEqual && getUrl().equals(otherRequest.getUrl());
            isEqual = isEqual && getHeaders().equals(otherRequest.getHeaders());
            isEqual = isEqual && getUsername().equals(otherRequest.getUsername());
            isEqual = isEqual && getPassword().equals(otherRequest.getPassword());
        }

        return isEqual;
    }
}
