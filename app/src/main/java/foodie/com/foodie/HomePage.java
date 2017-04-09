package foodie.com.foodie;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Observer;

public class HomePage extends AppCompatActivity implements APIResponseObserver, LocationObserver {

    private final static String TAG="HOMEPAGE";
    final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 1000;
    private GPSLocation gpsLocation = null;
    private boolean firstLaunch = true;
    private APIResponseSubject apiResponseSubject = new APIResponseSubject();
    private LocationSubject locationSubject = new LocationSubject();
    private RestaurantAPI restSearch;
    private JsonParser parser;
    private RestaurantList restaurantList;
    private Intent displayRest = new Intent(this, DisplayRestaurant.class);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //asks for locations permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_LOCATION);
        }

        gpsLocation = new GPSLocation(this, locationSubject);
        restSearch = new RestaurantAPI(this, apiResponseSubject);
        parser = new JsonParser();
        apiResponseSubject.attach(this);
        locationSubject.attach(this);
        gpsLocation.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Please enable locations permissions", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onLocationResponse(){
        restSearch.setLocation(gpsLocation.getLocation());
        Log.d(TAG, "STARTING RETRIEVAL");
        restSearch.search();
    }

    @Override
    public void onAPIResponse() {
        restaurantList = parser.parseResults(restSearch.getResult());
        Log.d(TAG, restaurantList.toString());
        Log.d(TAG, restaurantList.getCount() + "");

        if(restaurantList.getRestaurants() != null) {
            displayRest.putExtra("newRestaurants", restaurantList.getRestaurants());
        }

        Log.e(TAG, "REST SIZE" + restaurantList.getCount());
    }

    public void button(View view) {
        if(firstLaunch) {
            firstLaunch = false;
            displayRest.putExtra("isDoneRetrieving", restSearch.isDoneRetrieving());
            if(restaurantList.getRestaurants() != null) {
                displayRest.putExtra("restaurants", restaurantList.getRestaurants());
            }
            startActivity(displayRest);
        }
    }
}
