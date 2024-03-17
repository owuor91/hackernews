package io.github.owuor91.data.sql.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
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
