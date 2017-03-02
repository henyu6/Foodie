package foodie.com.foodie;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Henry on 2/23/2017.
 */
public class JsonParser {
    private final static String TAG="JSONPARSER";
    RestaurantList restaurants;

    public JsonParser() {
        restaurants = new RestaurantList();
    }

    private void parseRestaurantJson(JSONArray restaurantArray) throws JSONException {
        for(int i = 0; i < restaurantArray.length(); i++) {
            JSONObject currRest = restaurantArray.getJSONObject(i).getJSONObject("restaurant");
            if(currRest == null) return;

            Restaurant restaurant = new Restaurant();
            restaurant.id =  currRest.getInt("id");
            restaurant.name = currRest.getString("name");
            restaurant.url = currRest.getString("url");
            restaurant.address = currRest.getJSONObject("location").getString("address");
            restaurant.cuisines = currRest.getString("cuisines");
            restaurant.costForTwo = currRest.getInt("average_cost_for_two");
            restaurant.priceRange = currRest.getInt("price_range");
            restaurant.rating = currRest.getJSONObject("user_rating").getInt("aggregate_rating");
            restaurant.photoURL = currRest.getString("photos_url");
            restaurant.menuURL = currRest.getString("menu_url");
            restaurants.addRestaurant(restaurant);
        }
    }

    public RestaurantList parseResults(JSONObject result) {
        if(result == null) return restaurants;

        try {
            JSONArray restaurantArray = result.getJSONArray("restaurants");
            parseRestaurantJson(restaurantArray);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public RestaurantList getResults() {
        return restaurants;
    }
}
