package com.example.bradcampbell.app;

import android.os.Bundle;

import com.example.bradcampbell.app.events.NavigateToHello2Event;
import com.example.bradcampbell.app.hello1.HelloFragment1;
import com.example.bradcampbell.app.hello2.HelloFragment2;
import com.example.bradcampbell.library.PresenterActivity;
import com.squareup.otto.Subscribe;

import static com.example.bradcampbell.app.App.getBus;

public class MainActivity extends PresenterActivity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.root, new HelloFragment1())
                    .commit();
        }
    }

    @Override protected void onResume() {
        super.onResume();
        getBus(this).register(this);
    }

    @Override protected void onPause() {
        super.onPause();
        getBus(this).unregister(this);
    }

    @Subscribe public void onNavigateToHello2Event(NavigateToHello2Event event) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.root, new HelloFragment2())
                .commit();
    }
}
