package io.github.owuor91.hackernews.di.fragment;

import dagger.Subcomponent;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Subcomponent(modules = FragmentModule.class) public interface FragmentComponent {

  @Subcomponent.Builder interface Builder{
    Builder fragmentModule(FragmentModule fragmentModule);
    FragmentComponent build();
  }
}
