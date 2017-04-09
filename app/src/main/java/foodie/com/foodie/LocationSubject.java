package foodie.com.foodie;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry on 4/9/2017.
 */
public class LocationSubject {
    public List<LocationObserver> observers = new ArrayList<>();
    public JSONObject response;

    public void attach(LocationObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for(LocationObserver ob: observers) {
            ob.onLocationResponse();
        }
    }
}
