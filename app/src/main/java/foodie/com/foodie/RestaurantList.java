package foodie.com.foodie;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Henry on 3/1/2017.
 */
public class RestaurantList implements Serializable {
    private ArrayList<Restaurant> restaurants;

    public RestaurantList() {
        restaurants = new ArrayList<>();
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public ArrayList getRestaurants() {
        return restaurants;
    }

    public int getCount() {
        return restaurants.size();
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < restaurants.size(); i++) {
            if(str.equals(""))
                str = str + restaurants.get(i).name;
            else
                str = str + ", " + restaurants.get(i).name;
        }
        return str;
    }
}
