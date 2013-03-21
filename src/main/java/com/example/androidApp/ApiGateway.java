package com.example.androidApp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApiGateway {
    private static final Http http = new Http();

    public void performRequest(ApiRequest request, ApiResponseCallbacks callbacks) {
        new ApiRequestTask(callbacks).execute(request);
    }

    private class ApiRequestTask extends AsyncTask<ApiRequest, Void, ApiResponse> {
        private ApiResponseCallbacks callbacks;

        public ApiRequestTask(ApiResponseCallbacks callbacks) {
            this.callbacks = callbacks;
        }

        @Override
        protected ApiResponse doInBackground(ApiRequest... apiRequests) {
            ApiRequest apiRequest = apiRequests[0];
            InputStream responseBody = null;

            try {
                Http.Response response = http.get(apiRequest.getUrl(), apiRequest.getHeaders(), null, null);
                responseBody = response.getResponseBody();

                return new ApiResponse(response.getStatusCode(), responseBody);
            }

            catch (Exception e) {
                return new ApiResponse(-1, null);
            }

            finally {
                if (responseBody != null) {
                    try { responseBody.close(); } catch (IOException ignored) { }
                }
            }
        }

        @Override
        protected void onPostExecute(ApiResponse apiResponse) {
            this.callbacks.onComplete(apiResponse);

            if (apiResponse.isSuccess()) { this.callbacks.onSuccess(apiResponse); }
            if (apiResponse.isFailure()) { this.callbacks.onFailure(apiResponse); }
        }
    }
}