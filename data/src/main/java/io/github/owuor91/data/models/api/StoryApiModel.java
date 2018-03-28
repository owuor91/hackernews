package io.github.owuor91.data.models.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoryApiModel {
  @SerializedName("by") private String by;

  @SerializedName("descendants") private int descendants;

  @SerializedName("id") private int id;

  @SerializedName("kids") private List<Integer> kids;

  @SerializedName("score") private int score;

  @SerializedName("text") private String text;

  @SerializedName("time") private long time;

  @SerializedName("title") private String title;

  @SerializedName("type") private String type;

  @SerializedName("url") private String url;

  private String itemType;

  public StoryApiModel() {
  }

  private StoryApiModel(Builder builder) {
    setBy(builder.by);
    setDescendants(builder.descendants);
    setId(builder.id);
    setKids(builder.kids);
    setScore(builder.score);
    setText(builder.text);
    setTime(builder.time);
    setTitle(builder.title);
    setType(builder.type);
    setUrl(builder.url);
    setItemType(builder.itemType);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getBy() {
    return by;
  }

  public void setBy(String by) {
    this.by = by;
  }

  public int getDescendants() {
    return descendants;
  }

  public void setDescendants(int descendants) {
    this.descendants = descendants;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Integer> getKids() {
    return kids;
  }

  public void setKids(List<Integer> kids) {
    this.kids = kids;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  public static final class Builder {
    private String by;
    private int descendants;
    private int id;
    private List<Integer> kids;
    private int score;
    private String text;
    private long time;
    private String title;
    private String type;
    private String url;
    private String itemType;

    private Builder() {
    }

    public Builder withBy(String val) {
      by = val;
      return this;
    }

    public Builder withDescendants(int val) {
      descendants = val;
      return this;
    }

    public Builder withId(int val) {
      id = val;
      return this;
    }

    public Builder withKids(List<Integer> val) {
      kids = val;
      return this;
    }

    public Builder withScore(int val) {
      score = val;
      return this;
    }

    public Builder withText(String val) {
      text = val;
      return this;
    }

    public Builder withTime(long val) {
      time = val;
      return this;
    }

    public Builder withTitle(String val) {
      title = val;
      return this;
    }

    public Builder withType(String val) {
      type = val;
      return this;
    }

    public Builder withUrl(String val) {
      url = val;
      return this;
    }

    public Builder withItemType(String val) {
      itemType = val;
      return this;
    }

    public StoryApiModel build() {
      return new StoryApiModel(this);
    }
  }
}
