package io.github.owuor91.data.api;

import io.reactivex.Flowable;
import java.util.List;
import retrofit2.http.GET;

/**
 * Created by johnowuor on 21/03/2018.
 */

public interface ItemsApi {
  @GET("topstories.json") Flowable<List<Integer>> getTopStoryItems();

  @GET("showstories.json") Flowable<List<Integer>> getShowStoryItems();

  @GET("jobstories.json") Flowable<List<Integer>> getJobStoryItems();

  @GET("askstories.json") Flowable<List<Integer>> getAskStoryItems();
}
