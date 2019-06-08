package com.example.thisstickies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CatalogFragment extends Fragment {

    String[] data = {"a","b","c"};

    StickiesAdapter mAdapter = new StickiesAdapter(data);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);


    }
}
