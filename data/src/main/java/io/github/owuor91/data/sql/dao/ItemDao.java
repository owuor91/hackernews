package io.github.owuor91.data.sql.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.github.owuor91.data.models.db.ItemDbModel;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 22/03/2018.
 */

@Dao public interface ItemDao {
  @Query("SELECT * FROM items WHERE itemType = :itemType") Single<List<ItemDbModel>> getItems(String itemType);

  @Insert(onConflict = OnConflictStrategy.REPLACE) long[] insertItems(List<ItemDbModel> itemDbModels);

  @Delete int deleteItems(List<ItemDbModel> itemDbModels);

  @Query("SELECT * FROM items") Single<List<ItemDbModel>> getAllItems();
}
