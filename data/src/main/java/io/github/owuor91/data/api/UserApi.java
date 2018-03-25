package io.github.owuor91.data.api;

import io.github.owuor91.data.models.api.UserApiModel;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by johnowuor on 25/03/2018.
 */

public interface UserApi {
  @GET("user/{user_id}.json") Flowable<UserApiModel> getUser(@Path("user_id") String userId);
}
