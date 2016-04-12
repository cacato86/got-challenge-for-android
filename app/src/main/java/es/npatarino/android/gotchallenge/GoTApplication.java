package es.npatarino.android.gotchallenge;

import android.app.Application;
import android.util.Log;

import es.npatarino.android.gotchallenge.Di.Componenets.DaggerTaskComponent;
import es.npatarino.android.gotchallenge.Di.Componenets.TaskComponent;
import es.npatarino.android.gotchallenge.Di.Modules.ContextModule;
import es.npatarino.android.gotchallenge.Di.Modules.TaskModule;
import es.npatarino.android.gotchallenge.Engine.ApiUrls;

/**
 * Created by carloscarrasco on 11/4/16.
 */
public class GoTApplication extends Application {

    private TaskComponent mTaskComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("TE","ADFSFD");

        mTaskComponent = DaggerTaskComponent.builder()
                .contextModule(new ContextModule(this))
                .taskModule(new TaskModule(ApiUrls.CHARACTERS))
                .build();

    }

    public TaskComponent getTaskComponent() {
        return mTaskComponent;
    }

    public void setUrlTask(String url) {
        mTaskComponent = DaggerTaskComponent.builder()
                .contextModule(new ContextModule(this))
                .taskModule(new TaskModule(url))
                .build();
    }

}
