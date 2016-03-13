package es.npatarino.android.gotchallenge.Engine;

import android.app.Activity;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;

/**
 * Created by Usuario on 13/03/2016.
 */
public class TaskLauncher {
    private final TaskInterface task;
    private final Activity activity;

    public TaskLauncher(Activity activity, TaskInterface task) {
        this.activity = activity;
        this.task = task;
    }

    public void launchTask(final TaskResultCalback callback){
        task.executeTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                new SyncDataManager<>(activity).setData(value);
                callback.onResult(value);
            }

            @Override
            public void onError(Object value) {
                callback.onError(value);
            }
        });
    }

}
