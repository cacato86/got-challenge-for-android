package es.npatarino.android.gotchallenge.Engine;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import es.npatarino.android.gotchallenge.BuildConfig;
import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by carloscarrasco on 15/3/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16, manifest = "src/main/AndroidManifest.xml")
public class TaskManagerTest {

    @Test
    public void getTaskWhenNetworkIsOnline(){
        Context context= RuntimeEnvironment.application.getApplicationContext();
        TaskConfiguration configration = new TaskConfiguration();

        TaskInterface task = new TaskManager(context, configration, true).getTask();
        assertThat(task, instanceOf(TaskOkHttp.class));
    }

    @Test
    public void getTaskWhenNetworkIsOffline(){
        Context context= RuntimeEnvironment.application.getApplicationContext();
        TaskConfiguration configration = new TaskConfiguration();

        TaskInterface task = new TaskManager(context, configration, false).getTask();
        assertThat(task, instanceOf(TaskOffline.class));
    }
}