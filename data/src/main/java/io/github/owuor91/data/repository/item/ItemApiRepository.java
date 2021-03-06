package io.github.owuor91.data.repository.item;

import io.github.owuor91.data.api.ItemsApi;
import io.github.owuor91.data.mappers.ItemMapper;
import io.github.owuor91.data.util.OperationImpossibleException;
import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.repository.ItemRepository;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class ItemApiRepository implements ItemRepository {
  private ItemsApi itemsApi;
  private ItemRepository itemDbRepository;

  public ItemApiRepository(ItemsApi itemsApi, @Named(DIConstants.DB) ItemRepository itemDbRepository) {
    this.itemsApi = itemsApi;
    this.itemDbRepository = itemDbRepository;
  }

  @Override public Single<List<Item>> getTopItems() {
    return itemsApi.getTopStoryItems()
        .flatMap(Flowable::fromIterable).take(30)
        .map(integer -> ItemMapper.makeItemApiModel(integer, Constants.TOP_STORY))
        .map(ItemMapper::transformFromApi).toList();
  }

  @Override public Single<List<Item>> getShowItems() {
    return itemsApi.getShowStoryItems()
        .flatMap(Flowable::fromIterable)
        .take(30)
        .map(integer -> ItemMapper.makeItemApiModel(integer, Constants.SHOW_STORY))
        .map(ItemMapper::transformFromApi)
        .toList();
  }

  @Override public Single<List<Item>> getJobItems() {
    return itemsApi.getJobStoryItems()
        .flatMap(Flowable::fromIterable)
        .take(30)
        .map(integer -> ItemMapper.makeItemApiModel(integer, Constants.JOB_STORY))
        .map(ItemMapper::transformFromApi)
        .toList();
  }

  @Override public Single<List<Item>> getAskItems() {
    return itemsApi.getAskStoryItems()
        .flatMap(Flowable::fromIterable)
        .take(30)
        .map(integer -> ItemMapper.makeItemApiModel(integer, Constants.ASK_STORY))
        .map(ItemMapper::transformFromApi)
        .toList();
  }

  @Override public Single<List<Item>> saveItems(List<Item> itemList) {
    return Single.error(new OperationImpossibleException());
  }

  @Override public Single<Object> deleteItems(List<Item> itemList) {
    return Single.error(new OperationImpossibleException());
  }

  @Override public Single<Object> deleteAllItems() {
    return Single.error(new OperationImpossibleException());
  }
}
