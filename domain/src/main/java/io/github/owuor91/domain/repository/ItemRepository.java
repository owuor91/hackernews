package io.github.owuor91.domain.repository;

import io.github.owuor91.domain.models.Item;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 20/03/2018.
 */

public interface ItemRepository {
  Single<List<Item>> getTopItems();

  Single<List<Item>> getShowItems();

  Single<List<Item>> getJobItems();

  Single<List<Item>> getAskItems();

  Single<List<Item>> saveItems(List<Item> itemList);

  Single<Object> deleteItems(List<Item> itemList);
}
