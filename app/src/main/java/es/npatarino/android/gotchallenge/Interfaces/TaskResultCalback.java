package es.npatarino.android.gotchallenge.Interfaces;

/**
 * Created by Carlos Carrasco on 13/03/2016.
 */
public interface TaskResultCalback<T> {
    void onResult(T value);

    void onError(T value);
}
