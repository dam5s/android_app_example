package com.example.androidApp;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 8:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ApiResponseCallbacks {
    public void onSuccess(ApiResponse response);
    public void onFailure(ApiResponse response);
    public void onComplete(ApiResponse response);
}
