package io.github.owuor91.domain.repository;

import io.github.owuor91.domain.models.Article;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 20/03/2018.
 */

public interface ArticleRepository {
  Single<List<Article>> getArticles();
}
