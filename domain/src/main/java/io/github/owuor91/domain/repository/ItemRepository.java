package io.github.owuor91.domain.repository;

import io.github.owuor91.domain.models.Item;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 20/03/2018.
 */

public interface ItemRepository {
  Single<List<Item>> getTopStories();

  Single<List<Item>> getShowStories();

  Single<List<Item>> getJobStories();

  Single<List<Item>> saveItems(List<Item> itemList);

  Single<Object> deleteItems(List<Item> itemList);
}
