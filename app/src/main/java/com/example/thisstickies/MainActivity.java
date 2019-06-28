package com.example.thisstickies;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView sticky;
    private TextView trash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sticky = findViewById(R.id.sticky);
        trash = findViewById(R.id.trash);

        sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                ft.replace(R.id.frame, new CatalogFragment(false));

                ft.commit();

            }
        });

        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                ft.replace(R.id.frame, new CatalogFragment(true));

                ft.commit();

            }
        });
    }
}
