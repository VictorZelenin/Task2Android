package dev.zelenin.fragmenttest.fragments.panel_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dev.zelenin.fragmenttest.R;
import dev.zelenin.fragmenttest.fragments.AddFragment;

/**
 * Created by victor on 29.06.16.
 */
public class AddPanelFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.add_panel_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button addButton = (Button) getView().findViewById(R.id.add_button);

        // TODO write add fragment !
        addButton.setOnClickListener((view) -> {
            AddFragment addFragment = new AddFragment();

            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, addFragment)
                    .remove(this)
                    .addToBackStack("tag")
                    .commit();
        });
    }

}
