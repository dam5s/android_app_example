package com.example.androidApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class HomeActivity extends RoboActivity {

    public

    @InjectView(R.id.loginEditText)
    EditText loginEditText;

    @InjectView(R.id.passwordEditText)
    EditText passwordEditText;

    @InjectView(R.id.signInButton)
    Button signInButton;

    @InjectView(R.id.signInResultTextView)
    TextView signInResultTextView;

    private

    View.OnFocusChangeListener emptyEditTextOnFocusListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View view, boolean focus) {
            if (focus) {
                EditText editText = (EditText) view;
                editText.setText("");
            }
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        loginEditText.setOnFocusChangeListener(emptyEditTextOnFocusListener);
        passwordEditText.setOnFocusChangeListener(emptyEditTextOnFocusListener);
    }
}
