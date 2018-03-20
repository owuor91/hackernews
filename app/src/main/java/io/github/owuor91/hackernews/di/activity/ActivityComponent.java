package io.github.owuor91.hackernews.di.activity;

import dagger.Subcomponent;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Subcomponent(modules = {ActivityModule.class}) public interface ActivityComponent {


  @Subcomponent.Builder interface Builder{
    Builder activityModule(ActivityModule activityModule);

    ActivityComponent build();
  }
}
