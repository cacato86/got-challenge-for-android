package es.npatarino.android.gotchallenge.Interfaces;

/**
 * Created by Carlos Carrasco on 13/03/2016.
 */
public interface SyncDataInterface<T> {
    void saveLocalData(String keyForLocalQuery, T data);

    T getLocaldata(String keyForLocalQuery);
}
