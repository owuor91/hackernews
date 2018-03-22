package io.github.owuor91.data.api;

import io.github.owuor91.data.models.api.StoryApiModel;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by johnowuor on 22/03/2018.
 */

public interface StoriesApi {
  @GET("item/{item_number}.json") Flowable<StoryApiModel> getStory(@Path("item_number") int item);
}
