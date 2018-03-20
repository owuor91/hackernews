package io.github.owuor91.data.mappers;

import io.github.owuor91.data.apiModels.ArticleApiModel;
import io.github.owuor91.domain.models.Article;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class ArticleMapper {
  public static Article transformFromApi(ArticleApiModel articleApiModel){
    return Article.newBuilder()
        .withAuthor(articleApiModel.getAuthor())
        .withDescription(articleApiModel.getDescription())
        .withPublishedAt(articleApiModel.getPublishedAt())
        .withTitle(articleApiModel.getTitle())
        .withUrl(articleApiModel.getUrl())
        .withUrlToImage(articleApiModel.getUrlToImage())
        .build();
  }
}
