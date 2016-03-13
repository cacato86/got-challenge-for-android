package es.npatarino.android.gotchallenge.Engine;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;

/**
 * Created by Usuario on 13/03/2016.
 */
public class TaskManager {
    private final TaskConfiguration taskConfigurator;
    private final Context context;

    public TaskManager(Context context, TaskConfiguration taskConfigurator) {
        this.context = context;
        this.taskConfigurator = taskConfigurator;
    }

    public TaskInterface getTask(){
        if (isNetworkAvailable()){
            
        }else{
            
        }
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
