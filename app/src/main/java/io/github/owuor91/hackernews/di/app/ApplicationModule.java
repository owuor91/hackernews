package io.github.owuor91.hackernews.di.app;

import dagger.Module;
import io.github.owuor91.hackernews.MyApplication;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;
import javax.inject.Singleton;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module(subcomponents = { ActivityComponent.class}) public class ApplicationModule {
  private final MyApplication myApplication;
  public ApplicationModule(MyApplication myApplication) {
    this.myApplication = myApplication;
  }
}
