package io.github.owuor91.data.di;

import android.arch.persistence.room.Room;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import io.github.owuor91.data.sql.HackerNewsDatabase;
import io.github.owuor91.data.sql.dao.ItemDao;
import io.github.owuor91.data.sql.dao.StoryDao;
import io.github.owuor91.domain.di.DIConstants;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module public class DatabaseModule {
  @Provides public HackerNewsDatabase provideHackerNewsDatabase(@Named(DIConstants.APP) Context context) {
    return Room.databaseBuilder(context, HackerNewsDatabase.class, "hackernews_db").build();
  }

  @Provides public ItemDao provideItemDao(HackerNewsDatabase hackerNewsDatabase) {
    return hackerNewsDatabase.itemDao();
  }

  @Provides public StoryDao provideStoryDao(HackerNewsDatabase hackerNewsDatabase) {
    return hackerNewsDatabase.storyDao();
  }
}
