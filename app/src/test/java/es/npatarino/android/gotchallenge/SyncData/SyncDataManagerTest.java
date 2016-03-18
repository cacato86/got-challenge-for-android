package es.npatarino.android.gotchallenge.SyncData;

import android.content.Context;
import android.content.SharedPreferences;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import es.npatarino.android.gotchallenge.BuildConfig;
import es.npatarino.android.gotchallenge.Engine.TaskConfiguration;
import es.npatarino.android.gotchallenge.R;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Carlos Carrasco on 15/03/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16, manifest = "src/main/AndroidManifest.xml")
public class SyncDataManagerTest {

    private String KEYFORQUERY = "QUERY";
    private String URL = "URL PRUEBA";


    @Test
    public void saveNormalDataAndCheckFinalValue(){
        Context context = RuntimeEnvironment.application;
        TaskConfiguration configuration = new TaskConfiguration();
        configuration.setUrl(KEYFORQUERY);
        SyncDataManager syncData = new SyncDataManager<>(context, configuration);
        syncData.setData(URL);

        assertThat((String)syncData.getData(), is(URL));
    }

    @Test
    public void saveEmptyDataAndCheckFinalValue(){
        Context context = RuntimeEnvironment.application;
        TaskConfiguration configuration = new TaskConfiguration();
        configuration.setUrl("");
        SyncDataManager syncData = new SyncDataManager<>(context, configuration);
        syncData.setData("");

        assertThat((String)syncData.getData(), is(""));
    }

    @Test(expected = Exception.class)
    public void saveNullDataOfflineAndCheckFinalValue(){
        Context context = RuntimeEnvironment.application;
        TaskConfiguration configuration = null;
        SyncDataManager syncData = new SyncDataManager<>(context, configuration);
        syncData.setData(URL);

        assertThat((String)syncData.getData(), is(URL));
    }
}