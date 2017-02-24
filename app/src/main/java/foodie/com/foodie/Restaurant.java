package foodie.com.foodie;

import android.content.Context;
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
public class Restaurant {
    private Context base;
    private String API_KEY = "95b29780d5b77c3fe740f185b8b1655e";
    public Restaurant(Context base) {
        this.base = base;
    }

    public void openURL() {
        RequestQueue requestQueue = Volley.newRequestQueue(base);
        String url = "https://developers.zomato.com/api/v2.1/categories";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Restaurant", "Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Restaurant", "Error:" + error.toString());
                    }
                }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user-key", "95b29780d5b77c3fe740f185b8b1655e");
                    params.put("Accept", "application/json");

                    return params;
                }
        };

        requestQueue.add(jsObjRequest);

    }
}
