package io.github.owuor91.data.repository.item;

import io.github.owuor91.data.mappers.ItemMapper;
import io.github.owuor91.data.sql.dao.ItemDao;
import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.repository.ItemRepository;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class ItemDbRepository implements ItemRepository {

  private ItemDao itemDao;

  public ItemDbRepository(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @Override public Single<List<Item>> getTopItems() {
    return itemDao.getItems(Constants.TOP_STORY)
        .flatMapPublisher(Flowable::fromIterable)
        .map(ItemMapper::transformFromDb)
        .toList();
  }

  @Override public Single<List<Item>> getShowItems() {
    return itemDao.getItems(Constants.SHOW_STORY)
        .flatMapPublisher(Flowable::fromIterable)
        .map(ItemMapper::transformFromDb)
        .toList();
  }

  @Override public Single<List<Item>> getJobItems() {
    return itemDao.getItems(Constants.JOB_STORY)
        .flatMapPublisher(Flowable::fromIterable)
        .map(ItemMapper::transformFromDb)
        .toList();
  }

  @Override public Single<List<Item>> getAskItems() {
    return itemDao.getItems(Constants.ASK_STORY)
        .flatMapPublisher(Flowable::fromIterable)
        .map(ItemMapper::transformFromDb)
        .toList();
  }

  @Override public Single<List<Item>> saveItems(List<Item> itemList) {
    return Single.just(itemList)
        .flatMapPublisher(Flowable::fromIterable)
        .map(ItemMapper::transformToDb)
        .toList()
        .map(itemDao::insertItems)
        .map(l -> itemList);
  }

  @Override public Single<Object> deleteItems(List<Item> itemList) {
    return Single.just(itemList)
        .flatMapPublisher(Flowable::fromIterable)
        .map(ItemMapper::transformToDb)
        .toList()
        .map(itemDao::deleteItems);
  }

  @Override public Single<Object> deleteAllItems() {
    return itemDao.getAllItems().map(itemDao::deleteItems);
  }
}
