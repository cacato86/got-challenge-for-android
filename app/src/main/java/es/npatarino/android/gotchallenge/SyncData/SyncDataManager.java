package es.npatarino.android.gotchallenge.SyncData;

import android.app.Activity;
import android.content.Context;

import es.npatarino.android.gotchallenge.Interfaces.SyncDataInterface;

/**
 * Created by Usuario on 13/03/2016.
 */
public class SyncDataManager<T> {
    private final SyncDataInterface syncTask;

    public SyncDataManager(Context context) {
        this.syncTask = createSyncTask(context);
    }

    public void setData(T data){
        syncTask.saveLocalData(data);
    }

    public T getData(){
        return (T) syncTask.getLocaldata();
    }

    private SyncDataInterface createSyncTask(Context context){
        return new SyncSharedPreferences(context);
    }
}
