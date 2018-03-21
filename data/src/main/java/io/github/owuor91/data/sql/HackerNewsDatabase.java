package io.github.owuor91.data.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import io.github.owuor91.data.dbModels.ItemDbModel;
import io.github.owuor91.data.sql.dao.ItemDao;

/**
 * Created by johnowuor on 22/03/2018.
 */

@Database(entities = { ItemDbModel.class }, version = 1) public abstract class HackerNewsDatabase extends RoomDatabase {
  public abstract ItemDao itemDao();
}
