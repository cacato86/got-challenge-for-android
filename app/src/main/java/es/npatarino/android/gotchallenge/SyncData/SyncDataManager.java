package es.npatarino.android.gotchallenge.SyncData;

import android.content.Context;

import es.npatarino.android.gotchallenge.Engine.TaskConfiguration;
import es.npatarino.android.gotchallenge.Interfaces.SyncDataInterface;

/**
 * Created by Carlos Carrasco on 13/03/2016.
 */
public class SyncDataManager<T> {
    private final SyncDataInterface syncTask;
    private final String keyForLocalQuery;

    public SyncDataManager(Context context, TaskConfiguration taskConfiguration) {
        this.syncTask = createSyncTask(context);
        keyForLocalQuery = taskConfiguration.getUrl();
    }

    public void setData(T data) {
        syncTask.saveLocalData(keyForLocalQuery, data);
    }

    public T getData() {
        return (T) syncTask.getLocaldata(keyForLocalQuery);
    }

    private SyncDataInterface createSyncTask(Context context) {
        return new SyncSQLite(context);
        //return new SyncSharedPreferences(context);
    }
}
