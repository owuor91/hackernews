package io.github.owuor91.hackernews.di.app;

import dagger.Component;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;
import javax.inject.Singleton;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Singleton @Component(modules = {ApplicationModule.class, AndroidModule.class}) public interface ApplicationComponent {
  ActivityComponent.Builder activityComponentBuilder();
}
