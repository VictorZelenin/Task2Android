package dev.zelenin.fragmenttest;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.zelenin.fragmenttest.info_fragments.AssignFragment;
import dev.zelenin.fragmenttest.info_fragments.ImageFragment;
import dev.zelenin.fragmenttest.info_fragments.TextFragment;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }
}
