package io.github.owuor91.hackernews.di.activity;

import dagger.Module;
import io.github.owuor91.hackernews.di.adapter.AdapterComponent;
import io.github.owuor91.presentation.di.PresenterModule;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module(includes = PresenterModule.class, subcomponents = { AdapterComponent.class })
public class ActivityModule {
}
