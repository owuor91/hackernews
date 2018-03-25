package io.github.owuor91.domain.repository;

import io.github.owuor91.domain.models.User;
import io.reactivex.Single;

/**
 * Created by johnowuor on 25/03/2018.
 */

public interface UserRepository {
  Single<User> getUser(String userId);
}
