package com.example.androidApp.support;


import com.example.androidApp.*;
import com.example.androidApp.api.ApiGateway;
import com.example.androidApp.api.ApiRequest;
import com.example.androidApp.api.ApiResponse;
import com.example.androidApp.api.ApiResponseCallbacks;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: dam5s
 * Date: 26/02/13
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class FakeApiGateway extends ApiGateway {
    public ArrayList<ApiResponse> stubbedApiResponses;
    public ArrayList<ApiRequest> stubbedApiRequests;

    public FakeApiGateway() {
        stubbedApiResponses = new ArrayList<ApiResponse>();
        stubbedApiRequests = new ArrayList<ApiRequest>();
    }

    public void stubResponseForRequest(ApiResponse apiResponse, ApiRequest apiRequest) {
        stubbedApiResponses.add(apiResponse);
        stubbedApiRequests.add(apiRequest);
    }

    public void stubAuthenticationSignInAsSuccess(String username, String password) {
        ApiResponse response = new ApiResponse(200, "");
        ApiRequest request = new AuthenticationRequest(username, password);
        stubResponseForRequest(response, request);
    }

    public void stubAuthenticationSignInAsFailure(String username, String password) {
        ApiResponse response = new ApiResponse(400, "");
        ApiRequest request = new AuthenticationRequest(username, password);
        stubResponseForRequest(response, request);
    }

    @Override
    public void performRequest(ApiRequest apiRequest, final ApiResponseCallbacks responseCallbacks) {
        ApiRequest expectedRequest = stubbedApiRequests.remove(stubbedApiRequests.size() - 1);
        ApiResponse apiResponse = stubbedApiResponses.remove(stubbedApiResponses.size() - 1);

        assertThat(apiRequest).isEqualTo(expectedRequest);

        processResponse(apiResponse, responseCallbacks);
    }
}