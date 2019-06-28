package com.example.thisstickies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.thisstickies.room.Sticky;
import com.example.thisstickies.room.StickyViewModel;

import java.io.Serializable;

public class StickyActivity extends AppCompatActivity {

    public static final int EDIT_FINISHED = 1;
    private String topicString;
    private String stickyString;
    private StickyViewModel mWordViewModel;
    private Sticky mysticky;
    TextView topic;
    TextView sticky;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWordViewModel = ViewModelProviders.of(this).get(StickyViewModel.class);
        setContentView(R.layout.sticky);
         topic = findViewById(R.id.topic_view);
         sticky = findViewById(R.id.sticky_view);

        ImageView delete = findViewById(R.id.delete);

        mysticky = (Sticky) getIntent().getSerializableExtra("mysticky");

        topicString = mysticky.getMyTopic();
        stickyString = mysticky.getMySticky();
        if(mysticky.getIsTrash()==0){
            delete.setImageResource(R.drawable.delete);
        }else{
            delete.setImageResource(R.drawable.restore);
        }

        topic.setText(topicString);
        sticky.setText(stickyString);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysticky.setTrash();
                mWordViewModel.update(mysticky);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mysticky.getIsTrash() == 0) {
            /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
            MenuInflater inflater = getMenuInflater();
            /* Use the inflater's inflate method to inflate our menu layout to this menu */
            inflater.inflate(R.menu.menu, menu);
        }
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {
            // COMPLETED (46) Instead of setting the text to "", set the adapter to null before refreshing
            String shareBody = "Here is one of my stickies";
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody + "\n" + topicString + "\n" + stickyString);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
            return true;
        }

        if (id == R.id.edit) {
            Intent stickyIntent = new Intent(this, EditActivity.class);
            stickyIntent.putExtra("mysticky", (Serializable) mysticky);
            startActivityForResult(stickyIntent, EDIT_FINISHED);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_FINISHED && resultCode == RESULT_OK) {
            topicString = data.getStringExtra("topic");
            stickyString = data.getStringExtra("sticky");
            topic.setText(topicString);
            sticky.setText(stickyString);

        }
    }
}
