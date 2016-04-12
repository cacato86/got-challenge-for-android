package es.npatarino.android.gotchallenge.Di.Modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.npatarino.android.gotchallenge.Engine.TaskConfiguration;
import es.npatarino.android.gotchallenge.Engine.TaskLauncher;
import es.npatarino.android.gotchallenge.Engine.TaskManager;
import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;
import es.npatarino.android.gotchallenge.Utils.Utils;

/**
 * Created by carloscarrasco on 11/4/16.
 */
@Module
public class TaskModule {

    private String urlConfig;

    public TaskModule(String urlConfig) {
        this.urlConfig = urlConfig;
    }

    public String getUrlConfig() {
        return urlConfig;
    }

    public void setUrlConfig(String urlConfig) {
        this.urlConfig = urlConfig;
    }

    @Provides
    @Singleton
    TaskConfiguration provideTaskConfiguration() {
        TaskConfiguration config = new TaskConfiguration();
        config.setUrl(urlConfig);
        return config;
    }

    @Provides
    @Singleton
    SyncDataManager<Object> provideSyncDataManager(Context context, TaskConfiguration config) {
        SyncDataManager<Object> syncData = new SyncDataManager<>(context, config);
        return syncData;
    }

    @Provides
    @Singleton
    TaskInterface provideTaskManager(Context context, TaskConfiguration config) {
        TaskManager taskManager = new TaskManager(context, config, Utils.isNetworkAvailable(context));
        return taskManager.getTask();
    }

    @Provides
    @Singleton
    //@Named("launcher")
    TaskLauncher provideTaskLauncher(TaskInterface task, SyncDataManager<Object> syncData) {
        return new TaskLauncher(task, syncData);
    }
}
