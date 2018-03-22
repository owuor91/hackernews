package io.github.owuor91.data.models.api;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class ItemApiModel {
  private int item;

  private String itemType;

  public ItemApiModel() {
  }

  private ItemApiModel(Builder builder) {
    setItem(builder.item);
    setItemType(builder.itemType);
  }

  public static Builder newBuilder() {
    return new Builder();
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
    private int item;
    private String itemType;

    private Builder() {
    }

    public Builder withItem(int val) {
      item = val;
      return this;
    }

    public Builder withItemType(String val) {
      itemType = val;
      return this;
    }

    public ItemApiModel build() {
      return new ItemApiModel(this);
    }
  }
}
