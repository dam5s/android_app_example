package com.example.androidApp;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/20/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {
    private HomeActivity activity;

    @Before
    public void setUp() {
        activity = new HomeActivity();
        activity.onCreate(null);
    }

    @Test
    public void shouldHaveProperAppName() throws Exception {
        String appName = activity.getResources().getString(R.string.app_name);
        assertThat(appName).isEqualTo("AndroidApp");
    }

    @Test
    public void shouldInjectViews() {
        assertThat(activity.loginEditText.getText().toString()).isEqualTo("Login");
        assertThat(activity.passwordEditText.getText().toString()).isEqualTo("Password");
        assertThat(activity.signInButton.getText().toString()).isEqualTo("Sign in to Pivotal Tracker");
        assertThat(activity.signInResultTextView.getText().toString()).isEqualTo("");
    }

    @Test
    public void shouldEmptyLoginEditTextOnFocus() {
        activity.loginEditText.getOnFocusChangeListener().onFocusChange(activity.loginEditText, false);
        assertThat(activity.loginEditText.getText().toString()).isNotEqualTo("");

        activity.loginEditText.getOnFocusChangeListener().onFocusChange(activity.loginEditText, true);
        assertThat(activity.loginEditText.getText().toString()).isEqualTo("");
    }

    @Test
    public void shouldEmptyPasswordEditTextOnFocus() {
        activity.passwordEditText.getOnFocusChangeListener().onFocusChange(activity.passwordEditText, false);
        assertThat(activity.passwordEditText.getText().toString()).isNotEqualTo("");

        activity.passwordEditText.getOnFocusChangeListener().onFocusChange(activity.passwordEditText, true);
        assertThat(activity.passwordEditText.getText().toString()).isEqualTo("");
    }
}