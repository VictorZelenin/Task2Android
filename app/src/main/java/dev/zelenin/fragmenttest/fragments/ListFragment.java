package dev.zelenin.fragmenttest.fragments;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dev.zelenin.fragmenttest.R;
import dev.zelenin.fragmenttest.database.DatabaseHandler;
import dev.zelenin.fragmenttest.fragments.panel_fragments.EditPanelFragment;


/**
 * Created by victor on 28.06.16.
 */
public class ListFragment extends Fragment {

    private static final String LOG_TAG = "MY_LOGS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_fragment, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseHandler databaseHandler = new DatabaseHandler(this.getActivity());
        SQLiteDatabase database = databaseHandler.getWritableDatabase();
        List<String> cities = new ArrayList<>();
        Map<String, String> cityMap = new TreeMap<>();

        Cursor cursor = database.query("cities", null, null, null, null, null,
                DatabaseHandler.CITY_NAME_COLUMN);

        while (cursor.moveToNext()) {
            cities.add(cursor.getString(cursor.getColumnIndex(DatabaseHandler.CITY_NAME_COLUMN)));
            cityMap.put(cursor.getString(cursor.getColumnIndex(DatabaseHandler.CITY_NAME_COLUMN)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHandler.CITY_DESCRIPTION_COLUMN)));
        }


        ListView list = (ListView) getView().findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, new ArrayList<>(cityMap.keySet()));

        list.setAdapter(adapter);

        list.setOnItemClickListener((adapterView, view, pos, id) -> {
            Log.d(LOG_TAG, "Item: " + pos);

            TextFragment textFragment = new TextFragment();
            EditPanelFragment editFragment = new EditPanelFragment();

            Bundle bundle = new Bundle();
            setBundle(bundle, pos, cities, cityMap);

            textFragment.setArguments(bundle);
            editFragment.setArguments(bundle);

            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, textFragment)
                    .replace(R.id.button_container, editFragment)
                    .addToBackStack("tag")
                    .commit();
        });

        cursor.close();
        database.close();
        databaseHandler.close();
    }

    private void setBundle(Bundle bundle, int pos, List<String> cities, Map<String, String> map) {

        bundle.putString("city_name", cities.get(pos));
        bundle.putString("city_description", map.get(cities.get(pos)));
    }
}
