package io.github.owuor91.domain.models;

/**
 * Created by johnowuor on 21/03/2018.
 */

public class Item {
  private int itemId;

  public Item(int itemId) {
    this.itemId = itemId;
  }

  private Item(Builder builder) {
    setItemId(builder.itemId);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public static final class Builder {
    private int itemId;

    private Builder() {
    }

    public Builder withItemId(int val) {
      itemId = val;
      return this;
    }

    public Item build() {
      return new Item(this);
    }
  }
}
