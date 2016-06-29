package dev.zelenin.fragmenttest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by victor on 29.06.16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "test_db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_QUERY = "create table `cities`(" +
            " `id` integer primary key," +
            " `city_name` text," +
            " `city_description text`)";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
