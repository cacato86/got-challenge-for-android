package es.npatarino.android.gotchallenge.Engine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

/**
 * Created by carloscarrasco on 15/3/16.
 */
public class TaskConfigurationTest{

    private String URL = "Example Url";

    @Test
    public void checkTaskConfiguration(){
        TaskConfiguration taskConfiguration = new TaskConfiguration();
        taskConfiguration.setUrl(URL);
        assertThat(taskConfiguration.getUrl(), is(URL));
    }
}