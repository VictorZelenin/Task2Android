package dev.zelenin.fragmenttest.activities;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.zelenin.fragmenttest.R;
import dev.zelenin.fragmenttest.fragments.panel_fragments.AddPanelFragment;
import dev.zelenin.fragmenttest.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager myFragmentManager = getFragmentManager();

        ListFragment listFragment = new ListFragment();
        AddPanelFragment addFragment = new AddPanelFragment();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = myFragmentManager.beginTransaction();
            transaction.add(R.id.main_container, listFragment);
            transaction.add(R.id.button_container, addFragment);
            transaction.commit();
        }
    }


}
