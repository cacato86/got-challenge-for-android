package es.npatarino.android.gotchallenge.Engine;

import android.content.Context;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;

/**
 * Created by Usuario on 13/03/2016.
 */
public class TaskOfline implements TaskInterface {
    private final Context context;
    private TaskConfiguration taskConfigurator;

    public TaskOfline(Context context) {
        this.context = context;
    }

    @Override
    public TaskInterface createTask(TaskConfiguration taskConfigurator) {
        this.taskConfigurator = taskConfigurator;
        return this;
    }

    @Override
    public void executeTask(TaskResultCalback callbackResult) {
        callbackResult.onResult(new SyncDataManager<>(context, taskConfigurator).getData());
    }

    @Override
    public TaskConfiguration getTaskConfiguration() {
        return taskConfigurator;
    }
}
