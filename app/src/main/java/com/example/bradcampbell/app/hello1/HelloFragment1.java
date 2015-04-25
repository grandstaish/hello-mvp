package com.example.bradcampbell.app.hello1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bradcampbell.app.R;
import com.example.bradcampbell.app.events.NavigateToHello2Event;
import com.example.bradcampbell.library.BasePresenterFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.example.bradcampbell.app.App.*;

public class HelloFragment1 extends BasePresenterFragment<HelloView1, HelloPresenter1>
        implements HelloView1 {
    @InjectView(R.id.text) TextView textView;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hello1, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @NonNull @Override public HelloPresenter1 onCreatePresenter() {
        return new HelloPresenter1();
    }

    @Override public void show(CharSequence stuff) {
        textView.setText(stuff);
    }

    @Override public void goToNextScreen() {
        getBus(getActivity()).post(new NavigateToHello2Event());
    }

    @OnClick(R.id.button) public void buttonPressed() {
        getPresenter().buttonPressed();
    }
}
