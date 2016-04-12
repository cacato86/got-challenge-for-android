package es.npatarino.android.gotchallenge.Di.Modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carloscarrasco on 11/4/16.
 */
@Module
public class ContextModule {

    public Context context;

    public ContextModule(Application application) {
        this.context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return context;
    }

}
