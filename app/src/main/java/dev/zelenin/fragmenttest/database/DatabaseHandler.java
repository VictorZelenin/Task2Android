package dev.zelenin.fragmenttest.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import dev.zelenin.fragmenttest.R;

/**
 * Created by victor on 29.06.16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String CITY_NAME_COLUMN = "city_name";
    public static final String CITY_DESCRIPTION_COLUMN = "city_description";

    private static final String DB_NAME = "test_db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_QUERY = "create table `cities`(" +
            CITY_NAME_COLUMN + " text primary key," +
            CITY_DESCRIPTION_COLUMN + " text)";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
//        initializeDBbyTestData();
    }

    private void initializeDBbyTestData() {
        String[] cities = Resources.getSystem().getStringArray(R.array.cities);

        for (String city : cities) {
            ContentValues values = new ContentValues();
            values.put(CITY_NAME_COLUMN, city);
            values.put(CITY_DESCRIPTION_COLUMN, getDescription(city, cities));

            this.getReadableDatabase().insert("cities", null, values);
        }
    }

    private String getDescription(String city, String[] cities) {
        Resources system = Resources.getSystem();
        int descriptionId = system.getIdentifier(city.toLowerCase() + "_description",
                "string", "dev.zelenin.fragmenttest");

        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
