package io.github.owuor91.domain.models;

import java.util.List;

/**
 * Created by johnowuor on 25/03/2018.
 */

public class User {
  private String about;

  private int created;

  private int delay;

  private String userId;

  private int karma;

  private List<Integer> submitted;

  public User() {
  }

  private User(Builder builder) {
    setAbout(builder.about);
    setCreated(builder.created);
    setDelay(builder.delay);
    setUserId(builder.userId);
    setKarma(builder.karma);
    setSubmitted(builder.submitted);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public int getCreated() {
    return created;
  }

  public void setCreated(int created) {
    this.created = created;
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getKarma() {
    return karma;
  }

  public void setKarma(int karma) {
    this.karma = karma;
  }

  public List<Integer> getSubmitted() {
    return submitted;
  }

  public void setSubmitted(List<Integer> submitted) {
    this.submitted = submitted;
  }

  public static final class Builder {
    private String about;
    private int created;
    private int delay;
    private String userId;
    private int karma;
    private List<Integer> submitted;

    private Builder() {
    }

    public Builder withAbout(String val) {
      about = val;
      return this;
    }

    public Builder withCreated(int val) {
      created = val;
      return this;
    }

    public Builder withDelay(int val) {
      delay = val;
      return this;
    }

    public Builder withUserId(String val) {
      userId = val;
      return this;
    }

    public Builder withKarma(int val) {
      karma = val;
      return this;
    }

    public Builder withSubmitted(List<Integer> val) {
      submitted = val;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
