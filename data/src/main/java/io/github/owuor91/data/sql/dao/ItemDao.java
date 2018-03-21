package io.github.owuor91.data.sql.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import io.github.owuor91.data.dbModels.ItemDbModel;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 22/03/2018.
 */

@Dao public interface ItemDao {
  @Query("SELECT * FROM items WHERE itemType = :itemType") Single<List<ItemDbModel>> getItems(String itemType);

  @Insert(onConflict = OnConflictStrategy.REPLACE) long[] insertItems(List<ItemDbModel> itemDbModels);

  @Delete int deleteItems(List<ItemDbModel> itemDbModels);
}
