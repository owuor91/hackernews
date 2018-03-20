package io.github.owuor91.data.repository.article;

import io.github.owuor91.data.BuildConfig;
import io.github.owuor91.data.api.HackerNewsApi;
import io.github.owuor91.data.apiModels.ArticleResponse;
import io.github.owuor91.data.mappers.ArticleMapper;
import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.models.Article;
import io.github.owuor91.domain.repository.ArticleRepository;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class ArticleApiRepository implements ArticleRepository {
  private HackerNewsApi hackerNewsApi;

  public ArticleApiRepository(HackerNewsApi hackerNewsApi) {
    this.hackerNewsApi = hackerNewsApi;
  }

  @Override public Single<List<Article>> getArticles() {
    return hackerNewsApi.getArticles(Constants.HACKER_NEWS, BuildConfig.NEWS_API_KEY)
        .map(ArticleResponse::getArticleApiModelList)
        .flatMap(Flowable::fromIterable)
        .map(ArticleMapper::transformFromApi)
        .toList();
  }
}
