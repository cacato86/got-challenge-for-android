package es.npatarino.android.gotchallenge.Engine;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;

/**
 * Created by Usuario on 13/03/2016.
 */
public class TaskManager {
    private final TaskConfiguration taskConfigurator;
    private final Activity activity;

    public TaskManager(Activity activity, TaskConfiguration taskConfigurator) {
        this.activity = activity;
        this.taskConfigurator = taskConfigurator;
    }

    public TaskInterface getTask(){
        Log.e("ONLINE?",isNetworkAvailable(activity)+" /");
        if (isNetworkAvailable(activity)){
            return new TaskThread<String>().createTask(taskConfigurator);
        }else{
            return new TaskOffline(activity).createTask(taskConfigurator);
        }
    }

    public boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
