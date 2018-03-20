package io.github.owuor91.hackernews.di.activity;

import dagger.Subcomponent;
import io.github.owuor91.hackernews.ui.activities.HomeActivity;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Subcomponent(modules = {ActivityModule.class}) public interface ActivityComponent {

  void inject(HomeActivity homeActivity);

  @Subcomponent.Builder interface Builder{
    Builder activityModule(ActivityModule activityModule);

    ActivityComponent build();
  }
}
