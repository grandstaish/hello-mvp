package com.example.bradcampbell.library;

import android.support.annotation.NonNull;

public interface PresenterFactory<T extends Presenter> {
    /**
     * Create a new instance of a Presenter
     *
     * @return The Presenter instance
     */
    @NonNull T createPresenter();
}
