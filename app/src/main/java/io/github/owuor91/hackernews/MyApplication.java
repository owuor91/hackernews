package io.github.owuor91.hackernews;

import android.app.Application;
import io.github.owuor91.hackernews.di.app.AndroidModule;
import io.github.owuor91.hackernews.di.app.ApplicationComponent;
import io.github.owuor91.hackernews.di.app.ApplicationModule;
import io.github.owuor91.hackernews.di.app.DaggerApplicationComponent;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class MyApplication extends Application{
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .androidModule(new AndroidModule(this))
        .build();
  }
}
