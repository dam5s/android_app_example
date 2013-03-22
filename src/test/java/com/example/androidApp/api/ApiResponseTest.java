package com.example.androidApp.api;

import com.example.androidApp.api.ApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        response = new ApiResponse(200, ""){};
        assertThat(response.isSuccess()).isTrue();

        response = new ApiResponse(299, ""){};
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    public void isSuccess_shouldReturnFalseWhenStatusIsNotBetween200And299() {
        response = new ApiResponse(199, ""){};
        assertThat(response.isSuccess()).isFalse();

        response = new ApiResponse(300, ""){};
        assertThat(response.isSuccess()).isFalse();
    }

    @Test
    public void isFailure_shouldReturnTrueWhenStatusIs400OrGreater() {
        response = new ApiResponse(400, ""){};
        assertThat(response.isFailure()).isTrue();

        response = new ApiResponse(500, ""){};
        assertThat(response.isFailure()).isTrue();
    }

    @Test
    public void isFailure_shouldReturnFalseWhenStatusIsLessThan400() {
        response = new ApiResponse(399, ""){};
        assertThat(response.isFailure()).isFalse();

        response = new ApiResponse(200, ""){};
        assertThat(response.isFailure()).isFalse();
    }
}
