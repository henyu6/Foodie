package foodie.com.foodie;

/**
 * Created by Henry on 3/1/2017.
 */
public interface APIResponseObserver {
    APIResponseSubject subject = new APIResponseSubject();
    public abstract void onAPIResponse();
}
