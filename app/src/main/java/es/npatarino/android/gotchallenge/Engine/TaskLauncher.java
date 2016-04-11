package es.npatarino.android.gotchallenge.Engine;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;

/**
 * Created by Carlos Carrasco on 13/03/2016.
 */
public class TaskLauncher {
    private final TaskInterface task;
    private final SyncDataManager syncData;

    public TaskLauncher(TaskInterface task, SyncDataManager syncData) {
        this.task = task;
        this.syncData = syncData;
    }

    public void launchTask(final TaskResultCalback callback) {
        task.executeTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                callback.onResult(value);
                syncData.setData(value);
            }

            @Override
            public void onError(Object value) {
                callback.onError(value);
            }
        });
    }

}
