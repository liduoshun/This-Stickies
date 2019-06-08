package com.example.thisstickies;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView sticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sticky = findViewById(R.id.sticky);

        sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
                ft.replace(R.id.frame, new CatalogFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
                ft.commit();
            }
        });
    }
}
