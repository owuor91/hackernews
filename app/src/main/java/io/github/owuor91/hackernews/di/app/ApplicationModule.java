package io.github.owuor91.hackernews.di.app;

import android.content.Context;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.hackernews.MyApplication;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module(includes = AndroidModule.class, subcomponents = { ActivityComponent.class }) public class ApplicationModule {
  private final MyApplication myApplication;

  public ApplicationModule(MyApplication myApplication) {
    this.myApplication = myApplication;
  }

  @Provides @Named(DIConstants.APP) public Context provideAppContext() {
    return myApplication;
  }

  @Provides public Gson provideGson() {
    return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
  }
}
