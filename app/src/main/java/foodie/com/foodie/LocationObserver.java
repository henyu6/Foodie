package foodie.com.foodie;

/**
 * Created by Henry on 4/9/2017.
 */
public interface LocationObserver {
    LocationSubject subject = new LocationSubject();
    public abstract void onLocationResponse();
}
