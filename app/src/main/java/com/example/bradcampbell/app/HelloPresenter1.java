package com.example.bradcampbell.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class HelloPresenter1 extends BasePresenter<HelloView1> {
    private final DateFormat format = new SimpleDateFormat();
    private int serial = -1;

    @Override public void bindView(HelloView1 view) {
        super.bindView(view);
        view.show("Update #" + ++serial + " at " + format.format(new Date()));
    }

    public void buttonPressed() {
        view.goToNextScreen();
    }
}