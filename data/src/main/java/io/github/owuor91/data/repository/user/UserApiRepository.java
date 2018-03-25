package io.github.owuor91.data.repository.user;

import io.github.owuor91.data.api.UserApi;
import io.github.owuor91.data.mappers.UserMapper;
import io.github.owuor91.domain.models.User;
import io.github.owuor91.domain.repository.UserRepository;
import io.reactivex.Single;

/**
 * Created by johnowuor on 25/03/2018.
 */

public class UserApiRepository implements UserRepository {
  private UserApi userApi;

  public UserApiRepository(UserApi userApi) {
    this.userApi = userApi;
  }

  @Override public Single<User> getUser(String userId) {
    return userApi.getUser(userId).map(UserMapper::transformFromApi).firstOrError();
  }
}
