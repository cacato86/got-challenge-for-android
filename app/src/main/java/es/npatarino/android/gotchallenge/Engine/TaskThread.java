package es.npatarino.android.gotchallenge.Engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;

/**
 * Created by Carlos Carrasco on 13/03/2016.
 */
public class TaskThread<T> implements Runnable, TaskInterface {

    private TaskConfiguration taskConfigurator;
    private DataResponse finalResponse;

    @Override
    public TaskInterface createTask(final TaskConfiguration taskConfigurator) {
        this.taskConfigurator = taskConfigurator;
        return this;
    }

    @Override
    public TaskConfiguration getTaskConfiguration() {
        return taskConfigurator;
    }

    @Override
    public void executeTask(TaskResultCalback callbackResult) {
        try {
            Thread thread = new Thread(this);
            thread.start();
            thread.join();
            DataResponse response = (DataResponse) getValue();
            if (response.isFinishedCorrectly()) {
                callbackResult.onResult(response.getData());
            } else {
                callbackResult.onError(response.getData());
            }
        } catch (InterruptedException e) {
            callbackResult.onError(e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        try {
            URL obj = new URL(taskConfigurator.getUrl());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            finalResponse = new DataResponse(true, (T) response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            finalResponse = new DataResponse(false, (T) e.getMessage());
        }
    }

    private T getValue() {
        return (T) finalResponse;
    }

    private class DataResponse {
        private boolean finishedCorrectly;
        private T data;

        public DataResponse(boolean finishedCorrectly, T data) {
            this.finishedCorrectly = finishedCorrectly;
            this.data = data;
        }

        public boolean isFinishedCorrectly() {
            return finishedCorrectly;
        }

        public void setFinishedCorrectly(boolean finishedCorrectly) {
            this.finishedCorrectly = finishedCorrectly;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
