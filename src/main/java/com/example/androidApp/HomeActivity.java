package com.example.androidApp;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.androidApp.api.ApiResponse;
import com.example.androidApp.api.ApiResponseCallbacks;
import com.google.inject.Inject;
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

    @InjectView(R.id.locateMeButton)
    Button locateMeButton;

    @InjectView(R.id.locateMeResultTextView)
    TextView locateMeResultTextView;

    private

    @Inject
    AuthenticationGateway authenticationGateway;

    @Inject
    LocationManager locationManager;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            locateMeResultTextView.setText("Location (" +location.getLongitude()+ ", " +location.getLatitude()+ ")");
        }

        public void onStatusChanged(String s, int i, Bundle bundle) { }
        public void onProviderEnabled(String s) { }
        public void onProviderDisabled(String s) { }
    };

    View.OnClickListener locateMeButtonClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            locateMeResultTextView.setText(R.string.locateMeProgressResultText);

            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setSpeedRequired(false);
            criteria.setCostAllowed(true);

            locationManager.requestSingleUpdate(criteria, locationListener, Looper.myLooper());
        }
    };

    View.OnFocusChangeListener emptyEditTextOnFocusListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View view, boolean focus) {
            if (focus) {
                EditText editText = (EditText) view;
                editText.setText("");
            }
        }
    };

    View.OnClickListener signInButtonClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            String username = loginEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            signInResultTextView.setText(R.string.progressSignInResultText);

            authenticationGateway.signIn(username, password, new ApiResponseCallbacks() {
                public void onSuccess(ApiResponse response) {
                    signInResultTextView.setText(R.string.successSignInResultText);
                }

                public void onFailure(ApiResponse response) {
                    signInResultTextView.setText(R.string.errorSignInResultText);
                }

                public void onComplete(ApiResponse response) {
                }
            });
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
        signInButton.setOnClickListener(signInButtonClickListener);
        locateMeButton.setOnClickListener(locateMeButtonClickListener);
    }
}
