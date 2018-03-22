package io.github.owuor91.data.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import io.github.owuor91.data.models.db.ItemDbModel;
import io.github.owuor91.data.models.db.StoryDbModel;
import io.github.owuor91.data.sql.dao.ItemDao;
import io.github.owuor91.data.sql.dao.StoryDao;

/**
 * Created by johnowuor on 22/03/2018.
 */

@Database(entities = { ItemDbModel.class, StoryDbModel.class }, version = 1) public abstract class HackerNewsDatabase
    extends RoomDatabase {
  public abstract ItemDao itemDao();

  public abstract StoryDao storyDao();
}
