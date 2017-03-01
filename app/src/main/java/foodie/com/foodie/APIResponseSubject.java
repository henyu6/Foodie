package foodie.com.foodie;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Henry on 3/1/2017.
 */
public class APIResponseSubject {
    public List<APIResponseObserver> observers = new ArrayList<>();
    public JSONObject response;

    public void attach(APIResponseObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for(APIResponseObserver ob: observers) {
            ob.onAPIResponse();
        }
    }
}
