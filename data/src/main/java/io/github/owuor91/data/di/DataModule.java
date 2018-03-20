package io.github.owuor91.data.di;

import dagger.Module;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module(includes = {ApiModule.class, DatabaseModule.class, RepositoryModule.class}) public class DataModule {
}
