package io.github.owuor91.domain.models;

/**
 * Created by johnowuor on 21/03/2018.
 */

public class Item {
  private int id;

  private int item;

  private String itemType;

  public Item() {
  }

  private Item(Builder builder) {
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

    public Item build() {
      return new Item(this);
    }
  }
}
