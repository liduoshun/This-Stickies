package com.example.thisstickies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thisstickies.room.Sticky;
import com.example.thisstickies.room.StickyViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class CatalogFragment extends Fragment implements StickiesAdapter.StickyClickListener{

    private StickyViewModel mWordViewModel;

    private RecyclerView mRecyclerView;

    private boolean isTrash;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public CatalogFragment(boolean isTrash){
        this.isTrash = isTrash;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment, container, false);
        mWordViewModel = ViewModelProviders.of(this).get(StickyViewModel.class);

        FloatingActionButton fab = v.findViewById(R.id.fab);
        if(isTrash){
            fab.setVisibility(View.INVISIBLE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        StickiesAdapter mAdapter= new StickiesAdapter(this);

        mRecyclerView.setAdapter(mAdapter);
        LiveData<List<Sticky>> myPriceListener = mWordViewModel.getAllTopics(isTrash);
        myPriceListener.observe(this, price -> {
            // Update the UI.
            mAdapter.setData(price);
            mAdapter.notifyDataSetChanged();
        });



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Sticky word = new Sticky(data.getStringExtra(NewWordActivity.EXTRA_REPLY),data.getStringExtra(NewWordActivity.TOPIC_REPLY));
            Log.e("catalog", "onActivityResult: " + word.getMySticky() );
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    this.getContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void stickyOnclick(Sticky sticky) {
        Intent stickyIntent = new Intent(requireContext(),StickyActivity.class);
        stickyIntent.putExtra("mysticky", (Serializable) sticky);
        startActivity(stickyIntent);
    }


}
