package es.npatarino.android.gotchallenge.Engine;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;

import es.npatarino.android.gotchallenge.BuildConfig;
import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;
import es.npatarino.android.gotchallenge.UtilsTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by carloscarrasco on 15/3/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16, manifest = "src/main/AndroidManifest.xml")
public class TaskLauncherTest {

    @Test
    public void checkResultOkWhenLauchOneTask() throws IOException {

        final String expectedData = new UtilsTest().getStringFromAssets("data");
        TaskInterface fakeTask = Mockito.mock(TaskInterface.class);
        SyncDataManager fakeSyncData = Mockito.mock(SyncDataManager.class);

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                ((TaskResultCalback) invocation.getArguments()[0]).onResult(expectedData);
                return null;
            }
        }).when(fakeTask).executeTask(any(TaskResultCalback.class));

        new TaskLauncher(fakeTask, fakeSyncData).launchTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                assertThat((String) value, is(expectedData));
            }

            @Override
            public void onError(Object value) {
                Assert.fail("onError has been called when onResult should be called");
            }
        });
    }

    @Test
    public void checkResultErrorWhenLauchOneTask() throws IOException {

        final String expectedError = "Fake Error";
        TaskInterface fakeTask = Mockito.mock(TaskInterface.class);
        SyncDataManager fakeSyncData = Mockito.mock(SyncDataManager.class);

        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                ((TaskResultCalback) invocation.getArguments()[0]).onError(expectedError);
                return null;
            }
        }).when(fakeTask).executeTask(any(TaskResultCalback.class));

        new TaskLauncher(fakeTask, fakeSyncData).launchTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                Assert.fail("onResult has been called when onResult should be called");
            }

            @Override
            public void onError(Object value) {
                assertThat((String) value, is(expectedError));
            }
        });
    }
}