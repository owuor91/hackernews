package io.github.owuor91.hackernews.di.adapter;

import dagger.Subcomponent;
import io.github.owuor91.hackernews.ui.adapters.StoriesAdapter;
import io.github.owuor91.hackernews.ui.adapters.StoriesViewHolder;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Subcomponent(modules = { AdapterModule.class }) public interface AdapterComponent {

  void inject(StoriesAdapter storiesAdapter);

  void inject(StoriesViewHolder storiesViewHolder);

  @Subcomponent.Builder interface Builder{
    Builder adapterModule(AdapterModule adapterModule);

    AdapterComponent build();
  }
}
