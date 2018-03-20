package io.github.owuor91.data.apiModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class SourceApiModel {
  @SerializedName("id") private String id;

  @SerializedName("name") private String name;

  public SourceApiModel() {
  }

  private SourceApiModel(Builder builder) {
    setId(builder.id);
    setName(builder.name);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static final class Builder {
    private String id;
    private String name;

    private Builder() {
    }

    public Builder withId(String val) {
      id = val;
      return this;
    }

    public Builder withName(String val) {
      name = val;
      return this;
    }

    public SourceApiModel build() {
      return new SourceApiModel(this);
    }
  }
}
