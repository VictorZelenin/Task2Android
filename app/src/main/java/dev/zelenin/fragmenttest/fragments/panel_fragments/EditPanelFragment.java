package dev.zelenin.fragmenttest.fragments.panel_fragments;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dev.zelenin.fragmenttest.R;
import dev.zelenin.fragmenttest.database.DatabaseHandler;
import dev.zelenin.fragmenttest.fragments.EditFragment;
import dev.zelenin.fragmenttest.fragments.ListFragment;

/**
 * Created by victor on 29.06.16.
 */
public class EditPanelFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_panel_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button editButton = (Button) getView().findViewById(R.id.edit_button);
        Button removeButton = (Button) getView().findViewById(R.id.remove_button);
        Bundle bundle = getArguments();

        editButton.setOnClickListener(view -> {
            EditFragment editFragment = new EditFragment();

            // resending args
            editFragment.setArguments(bundle);


            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, editFragment)
                    .remove(this)
                    .addToBackStack("tag")
                    .commit();
        });

        // TODO write RemoveFragment!
        removeButton.setOnClickListener(view -> {
            DatabaseHandler databaseHandler = new DatabaseHandler(this.getActivity());
            SQLiteDatabase database = databaseHandler.getWritableDatabase();

            database.delete("cities", DatabaseHandler.CITY_NAME_COLUMN + " = ?",
                    new String[]{bundle.getString("city_name")});

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
