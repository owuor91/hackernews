package io.github.owuor91.data.mappers;

import io.github.owuor91.data.models.api.ItemApiModel;
import io.github.owuor91.data.models.db.ItemDbModel;
import io.github.owuor91.domain.models.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by johnowuor on 25/03/2018.
 */

@RunWith(JUnit4.class) public class ItemMapperTest {
  private ItemApiModel itemApiModel;
  private Item item;
  private ItemDbModel itemDbModel;
  private int itemId = 18973;
  private String itemType = "itemType";

  @Before public void setup() {
    itemApiModel = itemApiModel.newBuilder().withItem(18973).withItemType("itemType").build();

    item = item.newBuilder().withItem(18973).withItemType("itemType").build();

    itemDbModel = itemDbModel.newBuilder().withItem(18973).withItemType("itemType").build();
  }

  @Test public void shouldConvertApiItemToItem() {
    Item myItem = ItemMapper.transformFromApi(itemApiModel);

    Assert.assertEquals(myItem.getItem(), itemApiModel.getItem());
    Assert.assertEquals(myItem.getItemType(), itemApiModel.getItemType());
  }

  @Test public void shouldConvertItemToDbItem() {
    ItemDbModel myItemDbModel = ItemMapper.transformToDb(item);

    Assert.assertEquals(myItemDbModel.getItem(), item.getItem());
    Assert.assertEquals(myItemDbModel.getItemType(), item.getItemType());
  }

  @Test public void shouldConvertDbItemToItem() {
    Item myItem = ItemMapper.transformFromDb(itemDbModel);

    Assert.assertEquals(myItem.getItem(), itemDbModel.getItem());
    Assert.assertEquals(myItem.getItemType(), itemDbModel.getItemType());
  }

  @Test public void shouldMakeItemApiModel() {
    ItemApiModel myItemApiModel = ItemMapper.makeItemApiModel(itemId, itemType);

    Assert.assertEquals(myItemApiModel.getItem(), itemId);
    Assert.assertEquals(myItemApiModel.getItemType(), itemType);
  }
}
