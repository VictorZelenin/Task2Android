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
import dev.zelenin.fragmenttest.database.CRUDOperations;
import dev.zelenin.fragmenttest.database.City;
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
            String newCityName = cityNameField.getText().toString();
            String newCityDescription = cityDescriptionField.getText().toString();

            CRUDOperations dbOperator = new CRUDOperations(new DatabaseHandler(getActivity()));

            dbOperator.updateCity(cityName, new City(newCityName, newCityDescription));

            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new ListFragment())
                    .replace(R.id.button_container, new AddPanelFragment())
                    .addToBackStack("tag")
                    .commit();
        });
    }
}
