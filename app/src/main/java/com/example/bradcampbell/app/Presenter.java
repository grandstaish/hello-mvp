package com.example.bradcampbell.app;

public interface Presenter<T> {
    void bindView(T view);
    void unbindView();
}
