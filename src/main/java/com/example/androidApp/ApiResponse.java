package com.example.androidApp;

import java.io.ByteArrayInputStream;
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

    public ApiResponse(int status, String responseBodyString) {
        this.status = status;
        try {
            this.responseBody = new ByteArrayInputStream(responseBodyString.getBytes("UTF-8"));
        }
        catch (Exception e) {}
    }

    public boolean isSuccess() {
        return status >= 200 && status < 300;
    }

    public boolean isFailure() {
        return status >= 400;
    }
}
