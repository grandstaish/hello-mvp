package com.example.bradcampbell.library;

import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class PresenterBundleUtils {
    private static final String TAG = PresenterBundleUtils.class.getSimpleName();
    private static final String KEY_PREFIX = PresenterBundle.class.getName() + "-";

    private PresenterBundleUtils() {
        // No instances
    }

    @SuppressWarnings("unchecked") // Handled internally
    public static PresenterBundle getPresenterBundle(Bundle savedInstanceState, long presenterId) {
        HashMap<String, Object> map = null;
        if (savedInstanceState != null) {
            try {
                map = (HashMap<String, Object>) savedInstanceState
                        .getSerializable(KEY_PREFIX + presenterId);
            } catch (ClassCastException e) {
                Log.e(TAG, "", e);
            }
        }
        PresenterBundle result = null;
        if (map != null) {
            result = new PresenterBundle();
            result.setMap(map);
        }
        return result;
    }

    public static void setPresenterBundle(Bundle outState, PresenterBundle presenterBundle,
                                          long presenterId) {
        outState.putSerializable(KEY_PREFIX + presenterId, presenterBundle.getMap());
    }
}
