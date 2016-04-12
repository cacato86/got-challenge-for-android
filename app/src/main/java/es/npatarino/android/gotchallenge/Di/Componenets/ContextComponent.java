package es.npatarino.android.gotchallenge.Di.Componenets;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import es.npatarino.android.gotchallenge.Di.Modules.ContextModule;

/**
 * Created by carloscarrasco on 12/4/16.
 */
@Singleton
@Component(modules = ContextModule.class)
public interface ContextComponent {
    Context context();
}