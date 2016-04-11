package es.npatarino.android.gotchallenge.Engine;

import java.io.IOException;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.Utils.Utils;

/**
 * Created by carloscarrasco on 11/4/16.
 */
public class TaskStaticJson implements TaskInterface {
    private String response;

    @Override
    public TaskInterface createTask(TaskConfiguration taskConfigurator) {
        try {
            response = new Utils().getStringFromAssets("data");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public TaskConfiguration getTaskConfiguration() {
        return null;
    }

    @Override
    public void executeTask(TaskResultCalback callbackResult) {
        callbackResult.onResult(response);
    }
}
