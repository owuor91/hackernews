package io.github.owuor91.data.api;

import io.reactivex.Flowable;
import java.util.List;
import retrofit2.http.GET;

/**
 * Created by johnowuor on 21/03/2018.
 */

public interface ItemsApi {
  @GET("topstories.json") Flowable<List<Integer>> getTopStories();

  @GET("showstories.json") Flowable<List<Integer>> getShowStories();

  @GET("jobstories.json") Flowable<List<Integer>> getJobStories();

  @GET("askstories.json") Flowable<List<Integer>> getAskStories();
}
