package com.example.bradcampbell.app;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

public class App extends Application {
    private Bus bus;

    @Override public void onCreate() {
        super.onCreate();
        bus = new Bus();
    }

    public static Bus getBus(Context context) {
        return ((App)context.getApplicationContext()).bus;
    }
}
