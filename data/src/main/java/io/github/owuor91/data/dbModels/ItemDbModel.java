package io.github.owuor91.data.dbModels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by johnowuor on 22/03/2018.
 */

@Entity(tableName = "Items") public class ItemDbModel {
  @PrimaryKey(autoGenerate = true) private int id;

  private int item;

  private String itemType;

  public ItemDbModel() {
  }

  private ItemDbModel(Builder builder) {
    setId(builder.id);
    setItem(builder.item);
    setItemType(builder.itemType);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getItem() {
    return item;
  }

  public void setItem(int item) {
    this.item = item;
  }

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  public static final class Builder {
    private int id;
    private int item;
    private String itemType;

    private Builder() {
    }

    public Builder withId(int val) {
      id = val;
      return this;
    }

    public Builder withItem(int val) {
      item = val;
      return this;
    }

    public Builder withItemType(String val) {
      itemType = val;
      return this;
    }

    public ItemDbModel build() {
      return new ItemDbModel(this);
    }
  }
}
