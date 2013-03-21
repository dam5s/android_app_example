package com.example.androidApp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

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

    @Before
    public void setUp() throws Exception {
        apiGateway = new ApiGateway();
    }

    @Test
    public void testPerformRequestCallbacksOnSuccess() throws Exception {
        Robolectric.getBackgroundScheduler().pause();

        TestCallbacks callbacks = new TestCallbacks();
        ApiRequest request = new ApiRequest();
        request.setUrl("http://example.com/api");

        apiGateway.performRequest(request, callbacks);

        Robolectric.addPendingHttpResponse(200, "<response>success</response>");
        Robolectric.getBackgroundScheduler().runOneTask();

        assertThat(callbacks.didCallComplete).isTrue();
        assertThat(callbacks.didCallSuccess).isTrue();
        assertThat(callbacks.didCallFailure).isFalse();
    }

    @Test
    public void testPerformRequestCallbacksOnFailure() throws Exception {
        Robolectric.getBackgroundScheduler().pause();

        TestCallbacks callbacks = new TestCallbacks();
        ApiRequest request = new ApiRequest();
        request.setUrl("http://example.com/api");

        apiGateway.performRequest(request, callbacks);

        Robolectric.addPendingHttpResponse(400, "<response>failure</response>");
        Robolectric.getBackgroundScheduler().runOneTask();

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
