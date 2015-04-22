package com.example.bradcampbell.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class HelloPresenter2 extends BasePresenter<HelloView2> {
    private final DateFormat format = new SimpleDateFormat();
    private int serial = -1;

    @Override public void bindView(HelloView2 view) {
        super.bindView(view);
        view.show("Update #" + ++serial + " at " + format.format(new Date()));
    }
}