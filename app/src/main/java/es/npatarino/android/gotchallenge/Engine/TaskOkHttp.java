package es.npatarino.android.gotchallenge.Engine;


import java.io.IOException;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Usuario on 13/03/2016.
 */
public class TaskOkHttp implements TaskInterface {

    private TaskConfiguration taskConfigurator;
    private OkHttpClient client;

    @Override
    public TaskInterface createTask(final TaskConfiguration taskConfigurator) {
        this.taskConfigurator = taskConfigurator;
        client = new OkHttpClient();
        return this;
    }

    @Override
    public TaskConfiguration getTaskConfiguration() {
        return taskConfigurator;
    }

    @Override
    public void executeTask(TaskResultCalback callbackResult) {
        get(taskConfigurator, callbackResult);
    }

    private void get(TaskConfiguration taskConfigurator, final TaskResultCalback callbackResult) {
        Request request = new Request.Builder()
                .url(taskConfigurator.getUrl())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackResult.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                if (response.isSuccessful()) {
                    callbackResult.onResult(responseStr);
                } else {
                    callbackResult.onError(responseStr);
                }
            }
        });
    }

}
