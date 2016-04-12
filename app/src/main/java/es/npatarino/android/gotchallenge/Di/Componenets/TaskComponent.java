package es.npatarino.android.gotchallenge.Di.Componenets;

import javax.inject.Singleton;

import dagger.Component;
import es.npatarino.android.gotchallenge.Di.Modules.ContextModule;
import es.npatarino.android.gotchallenge.Di.Modules.TaskModule;
import es.npatarino.android.gotchallenge.Engine.TaskConfiguration;
import es.npatarino.android.gotchallenge.Engine.TaskLauncher;
import es.npatarino.android.gotchallenge.Fragments.GoTCharactersListFragment;
import es.npatarino.android.gotchallenge.Fragments.GoTHousesListFragment;
import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;

/**
 * Created by carloscarrasco on 11/4/16.
 */
@Singleton
@Component(dependencies = ContextModule.class, modules = {TaskModule.class})
public interface TaskComponent {
    void inject(GoTCharactersListFragment activity);
    void inject(GoTHousesListFragment activity);

    TaskConfiguration taskConfiguration();

    SyncDataManager<Object> syncDataManager();

    TaskInterface taskManager();

    TaskLauncher taskLauncher();
}
