package es.npatarino.android.gotchallenge.SyncData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by carloscarrasco on 16/3/16.
 */
public class SQLiteHelper<T> extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteExample.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATA_TABLE_NAME = "syncdata";
    public static final String DATA_COLUMN_ID = "_id";
    public static final String DATA_COLUMN_URL = "url";
    public static final String DATA_COLUMN_INFORMATION = "data";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATA_TABLE_NAME + "(" +
                        DATA_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        DATA_COLUMN_URL + " TEXT, " +
                        DATA_COLUMN_INFORMATION + " TEXT) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATA_TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String url, T data) {
        boolean result;
        int idColum = getId(url);
        if (idColum == -1) {
            result = insertData(url, data);
        } else {
            result = updateData(idColum, url, data);
        }
        return result;
    }

    public String getData(String url) {
        return getSQLData(url);
    }

    private boolean insertData(String url, T data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_COLUMN_URL, url);
        contentValues.put(DATA_COLUMN_INFORMATION, data.toString());
        db.insert(DATA_TABLE_NAME, null, contentValues);
        return true;
    }

    private boolean updateData(Integer id, String url, T data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_COLUMN_URL, url);
        contentValues.put(DATA_COLUMN_INFORMATION, data.toString());
        db.update(DATA_TABLE_NAME, contentValues, DATA_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    private String getSQLData(String url) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATA_TABLE_NAME + " WHERE " +
                DATA_COLUMN_URL + "=?", new String[]{url});

        String data = "";
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            data = cursor.getString(2);
            cursor.close();
        }

        return data;
    }

    private int getId(String url) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATA_TABLE_NAME + " WHERE " +
                DATA_COLUMN_URL + "=?", new String[]{url});

        int id = -1;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            id = Integer.parseInt(cursor.getString(0));
            cursor.close();
        }

        return id;
    }
}
