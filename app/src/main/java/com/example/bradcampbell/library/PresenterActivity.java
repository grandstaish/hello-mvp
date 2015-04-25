package com.example.bradcampbell.library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class PresenterActivity extends AppCompatActivity implements PresenterCache {
    private static final String NEXT_ID_KEY = "next-presenter-id";

    private NonConfigurationInstance nonConfigurationInstance;

    @Override public void onCreate(Bundle savedInstanceState) {
        nonConfigurationInstance =
                (NonConfigurationInstance) getLastCustomNonConfigurationInstance();
        if (nonConfigurationInstance == null) {
            long seed;
            if (savedInstanceState == null) {
                seed = 0;
            } else {
                seed = savedInstanceState.getLong(NEXT_ID_KEY);
            }
            nonConfigurationInstance = new NonConfigurationInstance(seed);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(NEXT_ID_KEY, nonConfigurationInstance.nextId.get());
    }

    @Override public Object onRetainCustomNonConfigurationInstance() {
        return nonConfigurationInstance;
    }

    @Override public long generateId() {
        return nonConfigurationInstance.nextId.getAndIncrement();
    }

    @SuppressWarnings("unchecked") // Handled internally
    @Override public final <T extends Presenter> T getPresenter(long index) {
        T p;
        try {
            p = (T) nonConfigurationInstance.presenters.get(index);
        } catch (ClassCastException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override public void setPresenter(long index, Presenter presenter) {
        nonConfigurationInstance.presenters.put(index, presenter);
    }

    private static class NonConfigurationInstance {
        private Map<Long, Presenter> presenters;
        private AtomicLong nextId;
        public NonConfigurationInstance(long seed) {
            presenters = new HashMap<>();
            nextId = new AtomicLong(seed);
        }
    }
}
