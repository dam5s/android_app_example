package com.example.androidApp;

import com.example.androidApp.api.ApiRequest;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 9:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationRequest extends ApiRequest {
    public AuthenticationRequest(String username, String password) {
        super();
        setUrl("https://www.pivotaltracker.com/services/v3/tokens/active");
        setMethod(GET);
        setUsername(username);
        setPassword(password);
    }
}
