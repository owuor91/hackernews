package io.github.owuor91.hackernews.di.fragment;

import dagger.Subcomponent;
import io.github.owuor91.hackernews.ui.fragments.AskStoriesFragment;
import io.github.owuor91.hackernews.ui.fragments.BaseFragment;
import io.github.owuor91.hackernews.ui.fragments.JobStoriesFragment;
import io.github.owuor91.hackernews.ui.fragments.ShowStoriesFragment;
import io.github.owuor91.hackernews.ui.fragments.TopStoriesFragment;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Subcomponent(modules = FragmentModule.class) public interface FragmentComponent {

  void baseInject(BaseFragment baseFragment);

  void inject(TopStoriesFragment topStoriesFragment);

  void inject(JobStoriesFragment jobStoriesFragment);

  void inject(ShowStoriesFragment showStoriesFragment);

  void inject(AskStoriesFragment askStoriesFragment);

  @Subcomponent.Builder interface Builder{
    Builder fragmentModule(FragmentModule fragmentModule);
    FragmentComponent build();
  }
}
