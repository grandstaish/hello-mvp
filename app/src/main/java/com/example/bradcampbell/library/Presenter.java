package com.example.bradcampbell.library;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface Presenter<T> {
    void onCreate(@Nullable PresenterBundle bundle);
    void onSaveInstanceState(@NonNull PresenterBundle bundle);
    void onDestroy();
    void bindView(T view);
    void unbindView();
}
