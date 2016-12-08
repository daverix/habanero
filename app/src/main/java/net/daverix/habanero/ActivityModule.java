package net.daverix.habanero;

import android.app.Activity;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity _activity;

    public ActivityModule(Activity activity) {
        _activity = activity;
    }

    @Provides
    public LayoutInflater provideLayoutInflater() {
        return _activity.getLayoutInflater();
    }
}
