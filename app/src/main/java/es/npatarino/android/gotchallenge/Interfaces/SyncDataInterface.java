package es.npatarino.android.gotchallenge.Interfaces;

/**
 * Created by Usuario on 13/03/2016.
 */
public interface SyncDataInterface<T> {
    void saveLocalData(T data);

    T getLocaldata();
}
