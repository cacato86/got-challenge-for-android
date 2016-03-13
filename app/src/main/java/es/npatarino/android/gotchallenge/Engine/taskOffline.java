package es.npatarino.android.gotchallenge.Engine;

import android.app.Activity;
import android.content.Context;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;

/**
 * Created by Usuario on 13/03/2016.
 */
public class TaskOffline implements TaskInterface {
    private final Activity activity;
    private TaskConfiguration taskConfigurator;

    public TaskOffline(Activity activity) {
        this.activity = activity;
    }

    @Override
    public TaskInterface createTask(TaskConfiguration taskConfigurator) {
        this.taskConfigurator = taskConfigurator;
        return this;
    }

    @Override
    public void executeTask(TaskResultCalback callbackResult) {
        callbackResult.onResult(new SyncDataManager<>(activity, taskConfigurator).getData());
    }

    @Override
    public TaskConfiguration getTaskConfiguration() {
        return taskConfigurator;
    }
}
