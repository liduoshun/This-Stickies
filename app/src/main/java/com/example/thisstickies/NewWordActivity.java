package com.example.thisstickies;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String TOPIC_REPLY = "REPLY";


    private EditText mEditWordView;

    private EditText mTopicView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sticky);
        mEditWordView = findViewById(R.id.edit_word);
        mTopicView = findViewById(R.id.edit_topic);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())||TextUtils.isEmpty(mTopicView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                    Log.e("newworld", "onClick: "+"wrong" );
                } else {
                    Log.e("newworld", "onClick: "+"okkkkk" );
                    String word = mEditWordView.getText().toString();
                    String topic = mTopicView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(TOPIC_REPLY, topic);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}
