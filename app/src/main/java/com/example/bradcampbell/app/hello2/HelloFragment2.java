package com.example.bradcampbell.app.hello2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bradcampbell.app.R;
import com.example.bradcampbell.library.BasePresenterFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HelloFragment2 extends BasePresenterFragment<HelloView2, HelloPresenter2>
        implements HelloView2 {
    @InjectView(R.id.text) TextView textView;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello2, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override @NonNull public HelloPresenter2 onCreatePresenter() {
        return new HelloPresenter2();
    }

    @Override public void show(CharSequence stuff) {
        textView.setText(stuff);
    }
}
