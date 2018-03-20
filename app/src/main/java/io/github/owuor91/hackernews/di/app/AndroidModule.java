package io.github.owuor91.hackernews.di.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import dagger.Module;
import dagger.Provides;
import io.github.owuor91.domain.di.DIConstants;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by johnowuor on 20/03/2018.
 */
@Module public class AndroidModule {
  private final Context context;

  public AndroidModule(Context context) {
    this.context = context;
  }

  @Singleton @Provides @Named(DIConstants.APP) public Context provideContext() {
    return context;
  }

  @Singleton @Provides public SharedPreferences provideSharedPreferences(@Named(DIConstants.APP) Context context){
    return context.getSharedPreferences("hacker_news_sharedprefs", Context.MODE_PRIVATE);
  }

  @Singleton @Provides public Resources provideResources(@Named(DIConstants.APP) Context context){
    return context.getResources();
  }
}
