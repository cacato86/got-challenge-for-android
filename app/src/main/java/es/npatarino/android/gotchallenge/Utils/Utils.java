package es.npatarino.android.gotchallenge.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Models.GoTCharacter;

/**
 * Created by Carlos Carrasco on 13/03/2016.
 */
public class Utils {
    public static ArrayList<GoTCharacter> filterList(ArrayList<GoTCharacter> models, String query) {
        query = query.toLowerCase();
        final ArrayList<GoTCharacter> filteredModelList = new ArrayList<>();
        if (models != null) {
            for (GoTCharacter model : models) {
                final String text = model.getName().toLowerCase();
                if (text.contains(query)) {
                    filteredModelList.add(model);
                }
            }
        }
        return filteredModelList;
    }

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    public String getStringFromAssets(String nameJson) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(nameJson + ".json");
        String jsonString = convertInputStreamToJson(in);
        return jsonString;
    }

    private String convertInputStreamToJson(InputStream is) throws IOException {
        String json = null;
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        return json;
    }
}
