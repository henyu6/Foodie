package foodie.com.foodie;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Henry on 2/23/2017.
 */
public class RestaurantAPI {
    private final static String TAG="Restaurant";
    private Context base;
    private String API_KEY = "95b29780d5b77c3fe740f185b8b1655e";
    private Location location;
    private int radius;
    public RestaurantAPI(Context base) {
        this.base = base;
    }

    private String generateURL () {
        String url = "https://developers.zomato.com/api/v2.1/search";
        Log.d(TAG, "GENERATING URL");
        if(location != null) {

            url = url + "?lat=" + location.getLatitude() + "&lon=" + location.getLongitude();
            if(radius != 0) {
                url = url + "&radius=" + radius;
            }
        }
        return url;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public void setRadius(int rad) {
        this.radius = rad;
    }

    public void search() {
        RequestQueue requestQueue = Volley.newRequestQueue(base);
        String url = generateURL();
        Log.d(TAG, "URL = " + url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error:" + error.toString());
                    }
                }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user-key", API_KEY);
                    params.put("Accept", "application/json");
                    return params;
                }
        };

        requestQueue.add(jsObjRequest);

    }
}
