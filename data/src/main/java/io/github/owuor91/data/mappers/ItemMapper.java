package io.github.owuor91.data.mappers;

import io.github.owuor91.data.apiModels.ItemApiModel;
import io.github.owuor91.data.dbModels.ItemDbModel;
import io.github.owuor91.domain.models.Item;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class ItemMapper {
  public static Item transformFromApi(ItemApiModel itemApiModel) {
    return Item.newBuilder().withItem(itemApiModel.getItem()).withItemType(itemApiModel.getItemType()).build();
  }

  public static ItemApiModel makeItemApiModel(int item, String itemType) {
    return ItemApiModel.newBuilder().withItem(item).withItemType(itemType).build();
  }

  public static ItemDbModel transformToDb(Item item) {
    return ItemDbModel.newBuilder().withItem(item.getItem()).withItemType(item.getItemType()).build();
  }

  public static Item transformFromDb(ItemDbModel itemDbModel) {
    return Item.newBuilder().withItem(itemDbModel.getItem()).withItemType(itemDbModel.getItemType()).build();
  }
}
