package es.npatarino.android.gotchallenge.SyncData;

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

    public SyncSharedPreferences(Context context) {
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Override
    public void saveLocalData(String keyForLocalQuery, Object data) {
        editor.putString(keyForLocalQuery, data.toString());
        editor.commit();
    }

    @Override
    public Object getLocaldata(String keyForLocalQuery) {
        return sharedPref.getString(keyForLocalQuery, "");
    }
}
