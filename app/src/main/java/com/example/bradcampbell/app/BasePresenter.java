package com.example.bradcampbell.app;

public abstract class BasePresenter<T> implements Presenter<T> {
    protected T view;

    @Override public void bindView(T view) {
        this.view = view;
    }

    @Override public void unbindView() {
        this.view = null;
    }
}
