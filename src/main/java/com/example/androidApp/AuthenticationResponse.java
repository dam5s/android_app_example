package com.example.androidApp;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationResponse extends ApiResponse {

    public AuthenticationResponse(int status, InputStream responseBody) {
        super(status, responseBody);
    }
}
