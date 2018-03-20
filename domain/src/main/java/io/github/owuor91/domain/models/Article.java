package io.github.owuor91.domain.models;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class Article {
  private Source source;

  private String author;

  private String title;

  private String description;

  private String url;

  private String urlToImage;

  private String publishedAt;

  public Article() {
  }

  private Article(Builder builder) {
    setSource(builder.source);
    setAuthor(builder.author);
    setTitle(builder.title);
    setDescription(builder.description);
    setUrl(builder.url);
    setUrlToImage(builder.urlToImage);
    setPublishedAt(builder.publishedAt);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Source getSource() {
    return source;
  }

  public void setSource(Source source) {
    this.source = source;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUrlToImage() {
    return urlToImage;
  }

  public void setUrlToImage(String urlToImage) {
    this.urlToImage = urlToImage;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(String publishedAt) {
    this.publishedAt = publishedAt;
  }

  public static final class Builder {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    private Builder() {
    }

    public Builder withSource(Source val) {
      source = val;
      return this;
    }

    public Builder withAuthor(String val) {
      author = val;
      return this;
    }

    public Builder withTitle(String val) {
      title = val;
      return this;
    }

    public Builder withDescription(String val) {
      description = val;
      return this;
    }

    public Builder withUrl(String val) {
      url = val;
      return this;
    }

    public Builder withUrlToImage(String val) {
      urlToImage = val;
      return this;
    }

    public Builder withPublishedAt(String val) {
      publishedAt = val;
      return this;
    }

    public Article build() {
      return new Article(this);
    }
  }
}
