package io.github.owuor91.data.di;

import dagger.Module;
import dagger.Provides;
import io.github.owuor91.data.api.HackerNewsApi;
import io.github.owuor91.data.repository.article.ArticleApiRepository;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.repository.ArticleRepository;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module public class RepositoryModule {
  @Provides @Named(DIConstants.API) ArticleRepository provideArticleApiRepository(HackerNewsApi hackerNewsApi){
    return new ArticleApiRepository(hackerNewsApi);
  }
}
