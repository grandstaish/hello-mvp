package com.example.bradcampbell.app.hello2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.bradcampbell.library.BasePresenter;
import com.example.bradcampbell.library.PresenterBundle;

import java.text.DateFormat;
import java.util.Date;

class HelloPresenter2 extends BasePresenter<HelloView2> {
    private static final String SERIAL_KEY = "serial";

    private final DateFormat format = DateFormat.getDateInstance();
    private int serial = -1;

    @Override public void bindView(HelloView2 view) {
        super.bindView(view);
        view.show("Update #" + ++serial + " at " + format.format(new Date()));
    }

    @Override public void onCreate(@Nullable PresenterBundle bundle) {
        if (bundle != null) {
            serial = bundle.getInt(SERIAL_KEY);
        }
    }

    @Override public void onSaveInstanceState(@NonNull PresenterBundle bundle) {
        bundle.putInt(SERIAL_KEY, serial);
    }
}