package com.example.androidApp;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApiResponse {
    private int status;
    private InputStream responseBody;

    public ApiResponse(int status, InputStream responseBody) {
        this.status = status;
        this.responseBody = responseBody;
    }

    public boolean isSuccess() {
        return status >= 200 && status < 300;
    }

    public boolean isFailure() {
        return status >= 400;
    }
}
