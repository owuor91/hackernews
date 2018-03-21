package io.github.owuor91.data.repository.article;

import io.github.owuor91.data.api.ItemsApi;
import io.github.owuor91.domain.repository.ItemRepository;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class ItemApiRepository implements ItemRepository {
  private ItemsApi itemsApi;

  public ItemApiRepository(ItemsApi itemsApi) {
    this.itemsApi = itemsApi;
  }

  @Override public Single<List<Integer>> getTopStories() {
    return itemsApi.getTopStories().firstOrError();
  }

  @Override public Single<List<Integer>> getShowStories() {
    return itemsApi.getShowStories().firstOrError();
  }

  @Override public Single<List<Integer>> getJobStories() {
    return itemsApi.getJobStories().firstOrError();
  }
}
