package dev.zelenin.fragmenttest.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.Map;

/**
 * Created by victor on 30.06.16.
 */
public class CRUDOperations {

    private DatabaseHandler databaseHandler;

    public CRUDOperations(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public boolean insertCity(City city) {
        SQLiteDatabase database = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.CITY_NAME_COLUMN, city.getName());
        values.put(DatabaseHandler.CITY_DESCRIPTION_COLUMN, city.getDescription());

        boolean result = database.insert("cities", null, values) > 0;
        database.close();

        return result;
    }

    public boolean updateCity(String oldCityName, City newValue) {
        SQLiteDatabase database = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.CITY_NAME_COLUMN, newValue.getName());
        values.put(DatabaseHandler.CITY_DESCRIPTION_COLUMN, newValue.getDescription());

        boolean result = database.update("cities", values, DatabaseHandler.CITY_DESCRIPTION_COLUMN + " = ?",
                new String[]{oldCityName}) > 0;
        database.close();

        return result;
    }

    public void getAllCities(List<String> allCities, Map<String, String> allCitiesWithDescriptions) {
        SQLiteDatabase database = databaseHandler.getReadableDatabase();

        Cursor cursor = database.query("cities", null, null, null, null, null,
                DatabaseHandler.CITY_NAME_COLUMN);

        while (cursor.moveToNext()) {
            allCities.add(cursor.getString(cursor.getColumnIndex(DatabaseHandler.CITY_NAME_COLUMN)));

            allCitiesWithDescriptions.put(cursor.getString(
                    cursor.getColumnIndex(DatabaseHandler.CITY_NAME_COLUMN)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHandler.CITY_DESCRIPTION_COLUMN)));
        }

        cursor.close();
        database.close();
    }

    public boolean deleteCity(City city) {
        SQLiteDatabase database = databaseHandler.getWritableDatabase();

        boolean result = database.delete("cities", DatabaseHandler.CITY_NAME_COLUMN + " = ?",
                new String[]{city.getName()}) > 0;
        database.close();

        return result;
    }
}
