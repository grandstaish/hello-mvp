package com.example.bradcampbell.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HelloFragment1 extends Fragment implements HelloView1 {
    private static final String TAG = HelloFragment1.class.getName();

    private PresenterCache presenterCache = PresenterCache.getInstance();
    private boolean isDestroyedBySystem;
    private HelloPresenter1 presenter;

    private Callbacks callbacks;
    private TextView textView;

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Callbacks) {
            callbacks = (Callbacks)activity;
        } else {
            throw new RuntimeException("HelloFragment1 must be attached to an Activity that" +
                    " implements its callbacks");
        }
    }

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
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(@NonNull View v) {
                presenter.buttonPressed();
            }
        });
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

    @Override public void goToNextScreen() {
        callbacks.onButtonPressed();
    }

    private PresenterFactory<HelloPresenter1> presenterFactory =
            new PresenterFactory<HelloPresenter1>() {
                @NonNull @Override public HelloPresenter1 createPresenter() {
                    return new HelloPresenter1();
                }
            };

    public interface Callbacks {
        void onButtonPressed();
    }
}
