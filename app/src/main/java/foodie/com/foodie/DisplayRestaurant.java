package foodie.com.foodie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DisplayRestaurant extends AppCompatActivity {
    private ArrayList<Restaurant> restaurants;
    private final static String TAG="DisplayRestaurant";
    private boolean isDoneRetrieving = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_restaurant);
        restaurants = (ArrayList<Restaurant>) getIntent().getSerializableExtra("restaurants");
        Log.d(TAG, "Restaurants Count: " + restaurants.size());
        Restaurant selected = selectRandom();
        displayRestaurant(selected);
    }

    private void getNewList() {
        boolean finish = getIntent().getBooleanExtra("isDoneRetrieving", false);
        if(!finish && !isDoneRetrieving) {
            if((ArrayList<Restaurant>) getIntent().getSerializableExtra("newRestaurants") == null)
                return;
            restaurants = (ArrayList<Restaurant>) getIntent().getSerializableExtra("newRestaurants");
            isDoneRetrieving = finish;
        }
    }
    private void displayRestaurant(Restaurant restaurant) {
        getNewList();
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(restaurant.name);
        TextView address = (TextView) findViewById(R.id.address);
        address.setText(restaurant.address);
    }

    public Restaurant selectRandom() {
        Random random = new Random();
        int ranInt = random.nextInt(restaurants.size());

        return restaurants.get(ranInt);
    }

}
