package es.npatarino.android.gotchallenge.SyncData;

import android.content.Context;

import es.npatarino.android.gotchallenge.Interfaces.SyncDataInterface;

/**
 * Created by carloscarrasco on 16/3/16.
 */
public class SyncSQLite implements SyncDataInterface {

    private final SQLiteHelper sqliteHelper;

    public SyncSQLite(Context context) {
        sqliteHelper = new SQLiteHelper(context);
    }

    @Override
    public void saveLocalData(String keyForLocalQuery, Object data) {
        sqliteHelper.addData(keyForLocalQuery, data);
    }

    @Override
    public Object getLocaldata(String keyForLocalQuery) {
        return sqliteHelper.getData(keyForLocalQuery);
    }
}
