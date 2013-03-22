package com.example.androidApp;

import com.example.androidApp.api.ApiGateway;
import com.example.androidApp.api.ApiRequest;
import com.example.androidApp.api.ApiResponse;
import com.example.androidApp.api.ApiResponseCallbacks;
import com.example.androidApp.support.ApplicationModuleWithMockApiGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import roboguice.inject.RoboInjector;

import java.util.Map;

import static com.example.androidApp.support.TestHelpers.updateApplicationInjectorWithModule;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 8:54 AM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(RobolectricTestRunner.class)
public class AuthenticationGatewayTest {
    private ApiGateway apiGateway;
    private AuthenticationGateway authenticationGateway;

    @Before
    public void setUp() throws Exception {
        RoboInjector injector = updateApplicationInjectorWithModule(new ApplicationModuleWithMockApiGateway());

        apiGateway = injector.getInstance(ApiGateway.class);
        authenticationGateway = injector.getInstance(AuthenticationGateway.class);
    }

    @Test
    public void signIn_shouldSetupTheCorrectRequest() throws Exception {
        ApiResponseCallbacks callbacks = new ApiResponseCallbacks() {
            public void onSuccess(ApiResponse response) { }
            public void onFailure(ApiResponse response) { }
            public void onComplete(ApiResponse response) { }
        };

        authenticationGateway.signIn("Foo", "Bar", callbacks);

        ArgumentCaptor<ApiRequest> requestArgumentCaptor = ArgumentCaptor.forClass(ApiRequest.class);
        verify(apiGateway).performRequest(requestArgumentCaptor.capture(), eq(callbacks));
        ApiRequest request = requestArgumentCaptor.getValue();
        Map<String, String> headers = request.getHeaders();

        assertThat(request.getUrl()).isEqualTo("https://www.pivotaltracker.com/services/v3/tokens/active");
        assertThat(request.getMethod()).isEqualTo(ApiRequest.GET);
        assertThat(request.getUsername()).isEqualTo("Foo");
        assertThat(request.getPassword()).isEqualTo("Bar");

        assertThat(headers.get("Content-Type")).isEqualTo("application/xml");
        assertThat(headers.get("Accept")).isEqualTo("application/xml");
    }
}
