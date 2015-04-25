package com.example.bradcampbell.library;

import android.os.Bundle;

import static com.example.bradcampbell.library.PresenterBundleUtils.*;

public class PresenterDelegate<V, T extends Presenter<V>> {
    private static final String PRESENTER_INDEX_KEY = "presenter-index";

    private T presenter;
    private long presenterId;
    private boolean isDestroyedBySystem;

    public void onCreate(PresenterCache cache, Bundle savedInstanceState,
                         PresenterFactory<T> presenterFactory) {
        if (savedInstanceState == null) {
            presenterId = cache.generateId();
        } else {
            presenterId = savedInstanceState.getLong(PRESENTER_INDEX_KEY);
        }
        presenter = cache.getPresenter(presenterId);
        if (presenter == null) {
            presenter = presenterFactory.createPresenter();
            cache.setPresenter(presenterId, presenter);
            PresenterBundle bundle = getPresenterBundle(savedInstanceState, presenterId);
            presenter.onCreate(bundle);
        }
    }

    public void onViewCreated(V view) {
        presenter.bindView(view);
    }

    public void onDestroyView() {
        presenter.unbindView();
    }

    public void onResume() {
        isDestroyedBySystem = false;
    }

    public void onSaveInstanceState(Bundle outState) {
        isDestroyedBySystem = true;
        outState.putLong(PRESENTER_INDEX_KEY, presenterId);
        PresenterBundle bundle = new PresenterBundle();
        presenter.onSaveInstanceState(bundle);
        setPresenterBundle(outState, bundle, presenterId);
    }

    public void onDestroy(PresenterCache cache) {
        if (!isDestroyedBySystem) {
            // User is exiting this view, remove presenter from the cache
            cache.setPresenter(presenterId, null);
            presenter.onDestroy();
        }
    }

    public T getPresenter() {
        return presenter;
    }
}
