package foodie.com.foodie;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Henry on 2/24/2017.
 */
public class GPSLocation implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private Context base;
    private GoogleApiClient mGoogleApiClient = null;
    private Location location;
    private final static String TAG="GPS LOCATION";
    private LocationSubject locationSubject;

    public GPSLocation(Context base, LocationSubject locationSubject) {
        this.base = base;

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(base)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        this.locationSubject = locationSubject;
    }

    public void start() {
        mGoogleApiClient.connect();
    }

    public void stop() {
        mGoogleApiClient.disconnect();
    }

    //gets the location from google location api
    private void getLastKnownLocation() throws SecurityException{
        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    //returns the current location
    public Location getLocation() {
        getLastKnownLocation();
        return location;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Locations API connected successfully");
        getLastKnownLocation();
        locationSubject.notifyAllObservers();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Locations API connected suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "Locations API connected failed");
        Log.e(TAG, connectionResult.getErrorMessage());
    }
}
