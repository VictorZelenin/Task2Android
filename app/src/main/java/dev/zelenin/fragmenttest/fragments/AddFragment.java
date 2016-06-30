package dev.zelenin.fragmenttest.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.zelenin.fragmenttest.R;
import dev.zelenin.fragmenttest.database.CRUDOperations;
import dev.zelenin.fragmenttest.database.City;
import dev.zelenin.fragmenttest.database.DatabaseHandler;
import dev.zelenin.fragmenttest.fragments.panel_fragments.AddPanelFragment;

/**
 * Created by victor on 29.06.16.
 */
public class AddFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 1
//        DatabaseHandler databaseHandler = new DatabaseHandler(this.getActivity());
//        SQLiteDatabase database = databaseHandler.getWritableDatabase();

        EditText cityNameField = (EditText) getView().findViewById(R.id.city_name_field);
        EditText cityDescriptionField = (EditText) getView().findViewById(R.id.city_description);
        Button addToDbButton = (Button) getView().findViewById(R.id.add_to_db_btn);

        addToDbButton.setOnClickListener(view -> {
            if (!(cityNameField.getText().toString().equals("")
                    || cityDescriptionField.getText().toString().equals(""))) {

                Log.d("MY LOG", cityNameField.toString());
                Log.d("MY LOG", cityDescriptionField.toString());

                String cityName = cityNameField.getText().toString();
                String cityDescription = cityDescriptionField.getText().toString();

                CRUDOperations dbOperator = new CRUDOperations(new DatabaseHandler(getActivity()));


                dbOperator.insertCity(new City(cityName, cityDescription));

//                ContentValues values = new ContentValues();
//                values.put(DatabaseHandler.CITY_NAME_COLUMN, cityNameField.getText().toString());
//                values.put(DatabaseHandler.CITY_DESCRIPTION_COLUMN, cityDescriptionField.getText().toString());
//                database.insert("cities", null, values);

                getActivity().getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, new ListFragment())
                        .replace(R.id.button_container, new AddPanelFragment())
                        .addToBackStack("tag")
                        .commit();

//                database.close();
//                databaseHandler.close();

            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Empty field(s)",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
