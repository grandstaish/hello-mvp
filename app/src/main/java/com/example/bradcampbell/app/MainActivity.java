package com.example.bradcampbell.app;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements HelloFragment1.Callbacks {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.root, new HelloFragment1())
                    .commit();
        }
    }

    @Override public void onButtonPressed() {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.root, new HelloFragment2())
                .commit();
    }
}
