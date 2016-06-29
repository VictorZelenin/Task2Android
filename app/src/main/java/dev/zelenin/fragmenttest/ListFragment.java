package dev.zelenin.fragmenttest;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashSet;


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

        ListView list = (ListView) getView().findViewById(R.id.list);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.cities, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);

        list.setOnItemClickListener((adapterView, view, pos, id) -> {
            Log.d(LOG_TAG, "Item: " + pos);
            TextFragment textFragment = new TextFragment();
            Bundle bundle = new Bundle();

            setBundle(bundle, pos);

            textFragment.setArguments(bundle);

            getActivity().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, textFragment)
                    .addToBackStack("tag")
                    .commit();
        });
    }

    private void setBundle(Bundle bundle, int pos) {
        String[] cities = getResources().getStringArray(R.array.cities);

        bundle.putString("city_name", cities[pos]);
        bundle.putString("city_description", getCityDescription(pos, cities));
    }

    private String getCityDescription(int pos, String[] cities) {
        return getString(getResources().getIdentifier(
                cities[pos].toLowerCase() + "_description", "string", "dev.zelenin.fragmenttest"));
    }

}
