package com.example.bradcampbell.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HelloFragment2 extends Fragment implements HelloView2 {
    private static final String TAG = HelloFragment2.class.getName();

    private PresenterCache presenterCache = PresenterCache.getInstance();
    private boolean isDestroyedBySystem;
    private HelloPresenter2 presenter;

    private TextView textView;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = presenterCache.getPresenter(TAG, presenterFactory);
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hello1, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.text);
        presenter.bindView(this);
        isDestroyedBySystem = false;
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        isDestroyedBySystem = true;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        presenter.unbindView();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (!isDestroyedBySystem) {
            presenterCache.removePresenter(TAG);
        }
    }

    @Override public void show(CharSequence stuff) {
        textView.setText(stuff);
    }

    private PresenterFactory<HelloPresenter2> presenterFactory =
            new PresenterFactory<HelloPresenter2>() {
                @NonNull @Override public HelloPresenter2 createPresenter() {
                    return new HelloPresenter2();
                }
            };
}
