package dev.zelenin.fragmenttest;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by victor on 28.06.16.
 */
public class ListFragment extends Fragment {

    private static final String LOG_TAG = "MY_LOGS";
    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_fragment, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = (ListView) getView().findViewById(R.id.list);

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
                    .commit();

        });
    }

    private void setBundle(Bundle bundle, int pos) {
        String[] cities = getResources().getStringArray(R.array.cities);

        switch (pos) {
            case 0:
                bundle.putString("city_name", cities[0]);
                bundle.putString("city_description", getString(R.string.kyiv_description));
                break;
            case 1:
                bundle.putString("city_name", cities[1]);
                bundle.putString("city_description", getString(R.string.london_description));
                break;
            case 2:
                bundle.putString("city_name", cities[2]);
                bundle.putString("city_description", getString(R.string.paris_description));
                break;
            case 3:
                bundle.putString("city_name", cities[3]);
                bundle.putString("city_description", getString(R.string.oslo_description));
                break;
            case 4:
                bundle.putString("city_name", cities[4]);
                bundle.putString("city_description", getString(R.string.lille_description));
                break;
            case 5:
                bundle.putString("city_name", cities[5]);
                bundle.putString("city_description", getString(R.string.barcelona_description));
                break;
            case 6:
                bundle.putString("city_name", cities[6]);
                bundle.putString("city_description", getString(R.string.manchester_description));
                break;
            case 7:
                bundle.putString("city_name", cities[7]);
                bundle.putString("city_description", getString(R.string.rome_description));
                break;
            case 8:
                bundle.putString("city_name", cities[8]);
                bundle.putString("city_description", getString(R.string.berlin_description));
                break;
            case 9:
                bundle.putString("city_name", cities[9]);
                bundle.putString("city_description", getString(R.string.warsaw_description));
                break;
            case 10:
                bundle.putString("city_name", cities[10]);
                bundle.putString("city_description", getString(R.string.lviv_description));
                break;
            case 11:
                bundle.putString("city_name", cities[11]);
                bundle.putString("city_description", getString(R.string.poznan_description));
                break;
            case 12:
                bundle.putString("city_name", cities[12]);
                bundle.putString("city_description", getString(R.string.moscow_description));
                break;
        }

    }
}
