package dev.zelenin.fragmenttest.info_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.zelenin.fragmenttest.R;

/**
 * Created by victor on 28.06.16.
 */
public class AssignFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.assign_fragment, null);
    }
}