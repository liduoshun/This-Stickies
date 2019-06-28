package com.example.thisstickies;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.thisstickies.room.Sticky;
import com.example.thisstickies.room.StickyViewModel;

public class EditActivity extends AppCompatActivity {

    private StickyViewModel mWordViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sticky);

        mWordViewModel = ViewModelProviders.of(this).get(StickyViewModel.class);

        TextView topic = findViewById(R.id.edit_topic);
        TextView sticky = findViewById(R.id.edit_word);

        Sticky mysticky = (Sticky) getIntent().getSerializableExtra("mysticky");

        topic.setText(mysticky.getMyTopic());
        sticky.setText(mysticky.getMySticky());

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String topicText = topic.getText().toString();
                String stickyText = sticky.getText().toString();
                mysticky.setMySticky(stickyText);
                mysticky.setMyTopic(topicText);
                mWordViewModel.update(mysticky);

                Intent replyIntent = new Intent();
                replyIntent.putExtra("topic",topicText);
                replyIntent.putExtra("sticky",stickyText);
                setResult(RESULT_OK, replyIntent);
                Log.e("ad", "onClick: aaaaaaaaaa" );
                finish();
            }
        });
    }
}
