package foodie.com.foodie;

import java.io.Serializable;

/**
 * Created by Henry on 3/1/2017.
 */
public class Restaurant implements Serializable{
    public int id;
    public String name;
    public String url;
    public String address;
    public String cuisines;
    public int costForTwo;
    public int priceRange;
    public int rating;
    public String photoURL;
    public String menuURL;

    public Restaurant() {
    }

}
