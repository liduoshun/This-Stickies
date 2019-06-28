package com.example.thisstickies;

import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thisstickies.room.Sticky;

import java.util.List;

public class StickiesAdapter extends RecyclerView.Adapter<StickiesAdapter.MyViewHolder> {

    private List<Sticky> mDataset;
    private StickyClickListener mlistener;
    interface StickyClickListener{
        void stickyOnclick(Sticky sticky);
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.stickies);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StickiesAdapter(StickyClickListener listener) {
        this.mlistener = listener;
    }

    public void setData(List<Sticky> data) {
        this.mDataset = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StickiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.stickies, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.textView.setText(mDataset.get(position).getMyTopic());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.stickyOnclick(mDataset.get(position));
            }
        });
    }

    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mDataset == null){
            return 0;
        }
        return mDataset.size();
    }

}
