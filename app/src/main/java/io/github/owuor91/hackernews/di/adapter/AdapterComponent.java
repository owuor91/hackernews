package io.github.owuor91.hackernews.di.adapter;

import dagger.Subcomponent;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Subcomponent(modules = { AdapterModule.class }) public interface AdapterComponent {

  @Subcomponent.Builder interface Builder{
    Builder adapterModule(AdapterModule adapterModule);

    AdapterComponent build();
  }
}
