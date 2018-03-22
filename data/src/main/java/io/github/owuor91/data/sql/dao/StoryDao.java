package io.github.owuor91.data.sql.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import io.github.owuor91.data.models.db.StoryDbModel;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 22/03/2018.
 */

@Dao public interface StoryDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) long insertStory(StoryDbModel storyDbModel);

  @Query("SELECT * FROM stories WHERE itemType = :itemType") Single<List<StoryDbModel>> getStoriesList(String itemType);

  @Delete int deleteStories(List<StoryDbModel> storyDbModels);

  @Query("SELECT * FROM stories") Single<List<StoryDbModel>> getAllStories();
}
