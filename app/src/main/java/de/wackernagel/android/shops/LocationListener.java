package de.wackernagel.android.shops;

import android.arch.lifecycle.LifecycleObserver;

public class LocationListener implements LifecycleObserver {
    private boolean enabled;

    public void enable() {
        enabled = true;

    }
}
