package dev.zelenin.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by victor on 28.06.16.
 */
public class TextFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.text_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        String cityNameStr = bundle.getString("city_name");
        String cityDesc = bundle.getString("city_description");

        TextView cityName = (TextView) getView().findViewById(R.id.city_name);
        TextView cityDescription = (TextView) getView().findViewById(R.id.city_description);
//
        cityName.setText(cityNameStr);
        cityDescription.setText(cityDesc);

    }
}
