package com.example.androidApp;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/20/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.ANDROID.assertThat;


@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {

    @Test
    public void shouldHaveProperAppName() throws Exception {
        String appName = new HomeActivity().getResources().getString(R.string.app_name);
        assertThat(appName).isEqualTo("AndroidApp");
    }

    @Test
    public void shouldInjectViews() {
        HomeActivity activity = new HomeActivity();
        activity.onCreate(null);

        assertThat(activity.loginEditText).hasText("Login");
    }
}