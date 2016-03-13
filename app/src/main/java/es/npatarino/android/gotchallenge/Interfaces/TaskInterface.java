package es.npatarino.android.gotchallenge.Interfaces;

import es.npatarino.android.gotchallenge.Engine.TaskConfiguration;
import es.npatarino.android.gotchallenge.Engine.TaskThread;

/**
 * Created by Usuario on 13/03/2016.
 */
public interface TaskInterface<T> {
    TaskInterface createTask(TaskConfiguration taskConfigurator);

    void executeTask(TaskResultCalback<T> callbackResult);
}
