package com.example.bradcampbell.library;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class BasePresenterFragment<V, T extends Presenter<V>> extends Fragment {
    private PresenterCache presenterCache;
    private PresenterDelegate<V, T> presenterDelegate = new PresenterDelegate<>();

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof PresenterCache) {
            presenterCache = (PresenterCache)activity;
        } else {
            throw new RuntimeException(getClass().getSimpleName() + " must be attached to " +
                    "an Activity that implements " + PresenterCache.class.getSimpleName());
        }
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterDelegate.onCreate(presenterCache, savedInstanceState, presenterFactory);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterDelegate.onViewCreated(getViewImpl());
    }

    @Override public void onResume() {
        super.onResume();
        presenterDelegate.onResume();
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenterDelegate.onSaveInstanceState(outState);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        presenterDelegate.onDestroyView();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        presenterDelegate.onDestroy(presenterCache);
    }

    @NonNull public abstract T onCreatePresenter();

    public T getPresenter() {
        return presenterDelegate.getPresenter();
    }

    @SuppressWarnings("unchecked") // Handled internally
    public V getViewImpl() {
        return (V) this;
    }

    public PresenterFactory<T> presenterFactory = new PresenterFactory<T>() {
        @NonNull @Override public T createPresenter() {
            return onCreatePresenter();
        }
    };
}
