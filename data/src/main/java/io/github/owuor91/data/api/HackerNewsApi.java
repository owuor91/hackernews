package io.github.owuor91.data.api;

import io.github.owuor91.data.apiModels.ArticleResponse;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by johnowuor on 20/03/2018.
 */

public interface HackerNewsApi {
  @GET("top-headlines") Flowable<ArticleResponse> getArticles(@Query("sources") String source,
      @Query("apiKey") String apiKey);
}
