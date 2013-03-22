package com.example.androidApp;

import com.example.androidApp.api.ApiGateway;
import com.example.androidApp.api.ApiResponseCallbacks;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationGateway {
    @Inject
    private ApiGateway apiGateway;

    public void signIn(String username, String password, ApiResponseCallbacks callbacks) {
        AuthenticationRequest request = new AuthenticationRequest(username, password);
        apiGateway.performRequest(request, callbacks);
    }
}
