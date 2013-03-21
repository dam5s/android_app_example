package com.example.androidApp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/21/13
 * Time: 9:19 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(RobolectricTestRunner.class)
public class ApiResponseTest {
    private ApiResponse response;

    @Test
    public void isSuccess_shouldReturnTrueWhenStatusIsBetween200And299() {
        response = new ApiResponse(200, null){};
        assertThat(response.isSuccess()).isTrue();

        response = new ApiResponse(299, null){};
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    public void isSuccess_shouldReturnFalseWhenStatusIsNotBetween200And299() {
        response = new ApiResponse(199, null){};
        assertThat(response.isSuccess()).isFalse();

        response = new ApiResponse(300, null){};
        assertThat(response.isSuccess()).isFalse();
    }

    @Test
    public void isFailure_shouldReturnTrueWhenStatusIs400OrGreater() {
        response = new ApiResponse(400, null){};
        assertThat(response.isFailure()).isTrue();

        response = new ApiResponse(500, null){};
        assertThat(response.isFailure()).isTrue();
    }

    @Test
    public void isFailure_shouldReturnFalseWhenStatusIsLessThan400() {
        response = new ApiResponse(399, null){};
        assertThat(response.isFailure()).isFalse();

        response = new ApiResponse(200, null){};
        assertThat(response.isFailure()).isFalse();
    }
}
