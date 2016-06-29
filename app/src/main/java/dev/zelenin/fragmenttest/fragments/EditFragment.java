package dev.zelenin.fragmenttest.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import dev.zelenin.fragmenttest.R;
import dev.zelenin.fragmenttest.database.DatabaseHandler;
import dev.zelenin.fragmenttest.fragments.panel_fragments.AddPanelFragment;


/**
 * Created by victor on 29.06.16.
 */
public class EditFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        DatabaseHandler databaseHandler = new DatabaseHandler(this.getActivity());
        SQLiteDatabase database = databaseHandler.getWritableDatabase();

        Bundle bundle = getArguments();
        String cityName = bundle.getString("city_name");
        String cityDescription = bundle.getString("city_description");

        EditText cityNameField = (EditText) getView().findViewById(R.id.update_cite_name_text);
        EditText cityDescriptionField = (EditText) getView().findViewById(R.id.update_city_description_text);
        Button updateButton = (Button) getView().findViewById(R.id.update_button);

        cityNameField.setText(cityName);
        cityDescriptionField.setText(cityDescription);

        // editing
        updateButton.setOnClickListener(view -> {
            ContentValues values = new ContentValues();
            values.put(DatabaseHandler.CITY_NAME_COLUMN, cityNameField.getText().toString());
            values.put(DatabaseHandler.CITY_DESCRIPTION_COLUMN, cityDescriptionField.getText().toString());
            database.update("cities", values, DatabaseHandler.CITY_NAME_COLUMN + " = ?",
                    new String[]{cityName});

            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new ListFragment())
                    .replace(R.id.button_container, new AddPanelFragment())
                    .addToBackStack("tag")
                    .commit();

            database.close();
            databaseHandler.close();
        });

    }
}
