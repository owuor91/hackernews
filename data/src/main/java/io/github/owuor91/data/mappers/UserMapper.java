package io.github.owuor91.data.mappers;

import io.github.owuor91.data.models.api.UserApiModel;
import io.github.owuor91.domain.models.User;

/**
 * Created by johnowuor on 25/03/2018.
 */

public class UserMapper {
  public static User transformFromApi(UserApiModel userApiModel) {
    return User.newBuilder()
        .withAbout(userApiModel.getAbout())
        .withCreated(userApiModel.getCreated())
        .withDelay(userApiModel.getDelay())
        .withKarma(userApiModel.getKarma())
        .withSubmitted(userApiModel.getSubmitted())
        .withUserId(userApiModel.getUserId())
        .build();
  }
}
