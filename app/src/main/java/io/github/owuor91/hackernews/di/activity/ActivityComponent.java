package io.github.owuor91.hackernews.di.activity;

import dagger.Subcomponent;
import io.github.owuor91.hackernews.di.adapter.AdapterComponent;
import io.github.owuor91.hackernews.di.fragment.FragmentComponent;
import io.github.owuor91.hackernews.ui.activities.BaseActivity;
import io.github.owuor91.hackernews.ui.activities.CommentsActivity;
import io.github.owuor91.hackernews.ui.activities.HomeActivity;
import io.github.owuor91.hackernews.ui.activities.UserActivity;

/**
 * Created by johnowuor on 20/03/2018.
 */

@ActivityScope @Subcomponent(modules = { ActivityModule.class }) public interface ActivityComponent {
  FragmentComponent.Builder fragmentBuilder();

  AdapterComponent.Builder adapterBuilder();

  void inject(HomeActivity homeActivity);

  void baseInject(BaseActivity baseActivity);

  void inject(CommentsActivity commentsActivity);

  void inject(UserActivity userActivity);

  @Subcomponent.Builder interface Builder{
    Builder activityModule(ActivityModule activityModule);

    ActivityComponent build();
  }
}
