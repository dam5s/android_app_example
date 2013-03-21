package com.example.androidApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class HomeActivity extends RoboActivity {

    public

    @InjectView(R.id.loginEditText) EditText loginEditText;
    @InjectView(R.id.passwordEditText) EditText passwordEditText;
    @InjectView(R.id.signInButton) Button signInButton;
    @InjectView(R.id.signInResultTextView) TextView signInResultTextView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
