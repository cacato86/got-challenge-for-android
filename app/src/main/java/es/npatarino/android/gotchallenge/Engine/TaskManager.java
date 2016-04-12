package es.npatarino.android.gotchallenge.Engine;

import android.content.Context;

import es.npatarino.android.gotchallenge.Engine.ConcreteTasks.TaskOffline;
import es.npatarino.android.gotchallenge.Engine.ConcreteTasks.TaskStaticJson;
import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;

/**
 * Created by Carlos Carrasco on 13/03/2016.
 */
public class TaskManager {

    private final TaskConfiguration taskConfigurator;
    private final Context context;
    private final boolean isNetworkActive;

    public TaskManager(Context context, TaskConfiguration taskConfigurator, boolean isNetworkActive) {
        this.context = context;
        this.taskConfigurator = taskConfigurator;
        this.isNetworkActive = isNetworkActive;
    }

    public TaskInterface getTask() {
        if (isNetworkActive) {
            return new TaskStaticJson().createTask(taskConfigurator);
            //return new TaskOkHttp().createTask(taskConfigurator);
            //return new TaskThread<String>().createTask(taskConfigurator);
        } else {
            return new TaskOffline(context).createTask(taskConfigurator);
        }
    }

}
