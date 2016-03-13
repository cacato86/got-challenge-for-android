package es.npatarino.android.gotchallenge.SyncData;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import es.npatarino.android.gotchallenge.Interfaces.SyncDataInterface;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Usuario on 13/03/2016.
 */
public class SyncSharedPreferences implements SyncDataInterface {

    private final SharedPreferences.Editor editor;
    private final SharedPreferences sharedPref;
    private String KEY = "LocalData";

    public SyncSharedPreferences(Context context) {
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Override
    public void saveLocalData(Object data) {
        editor.putString(KEY, data.toString());
        editor.commit();
    }

    @Override
    public Object getLocaldata() {
        return sharedPref.getString(KEY, "");
    }
}
