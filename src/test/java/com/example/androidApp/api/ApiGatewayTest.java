package com.example.androidApp.api;

import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.tester.org.apache.http.RequestMatcher;
import org.robolectric.tester.org.apache.http.TestHttpResponse;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 10:30 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(RobolectricTestRunner.class)
public class ApiGatewayTest {
    private ApiGateway apiGateway;
    private ApiRequest getRequest;

    private RequestMatcher getRequestMatcher = new RequestMatcher() {
        public boolean matches(HttpRequest request) {
            RequestLine requestLine = request.getRequestLine();
            assertThat(requestLine.getUri()).isEqualTo("http://example.com/api");
            assertThat(requestLine.getMethod()).isEqualTo("GET");
            return true;
        }
    };

    @Before
    public void setUp() throws Exception {
        apiGateway = new ApiGateway();

        getRequest = new ApiRequest();
        getRequest.setUrl("http://example.com/api");
        getRequest.setUsername("some username");
        getRequest.setPassword("p4ssword");
        getRequest.setMethod(ApiRequest.GET);
    }

    @Test
    public void testPerformRequestCallbacksOnSuccess() throws Exception {
        TestCallbacks callbacks = new TestCallbacks();

        Robolectric.addHttpResponseRule(getRequestMatcher, new TestHttpResponse(200, "<response>success</response>"));

        apiGateway.performRequest(getRequest, callbacks);

        assertThat(callbacks.didCallComplete).isTrue();
        assertThat(callbacks.didCallSuccess).isTrue();
        assertThat(callbacks.didCallFailure).isFalse();
    }

    @Test
    public void testPerformRequestCallbacksOnFailure() throws Exception {
        TestCallbacks callbacks = new TestCallbacks();

        Robolectric.addHttpResponseRule(getRequestMatcher, new TestHttpResponse(400, "<response>failure</response>"));

        apiGateway.performRequest(getRequest, callbacks);

        assertThat(callbacks.didCallComplete).isTrue();
        assertThat(callbacks.didCallSuccess).isFalse();
        assertThat(callbacks.didCallFailure).isTrue();
    }

    private class TestCallbacks implements ApiResponseCallbacks {
        public boolean didCallSuccess = false;
        public boolean didCallFailure = false;
        public boolean didCallComplete = false;

        public void onSuccess(ApiResponse response) {
            didCallSuccess = true;
        }

        public void onFailure(ApiResponse response) {
            didCallFailure = true;
        }

        public void onComplete(ApiResponse response) {
            didCallComplete = true;
        }
    }
}
