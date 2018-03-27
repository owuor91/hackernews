package io.github.owuor91.data.repository;

import io.github.owuor91.data.api.UserApi;
import io.github.owuor91.data.models.api.UserApiModel;
import io.github.owuor91.data.repository.user.UserApiRepository;
import io.github.owuor91.domain.models.User;
import io.reactivex.Flowable;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by johnowuor on 26/03/2018.
 */

@RunWith(MockitoJUnitRunner.class) public class UserApiRepositoryTest {
  @Mock UserApi userApi;

  @Test public void shouldGetUser() {
    UserApiModel userApiModel = UserApiModel.newBuilder()
        .withUserId("jill")
        .withAbout("about jill")
        .withCreated(24268223)
        .withDelay(9)
        .withKarma(32)
        .withSubmitted(new ArrayList<Integer>())
        .build();

    Mockito.when(userApi.getUser("jill")).thenReturn(Flowable.just(userApiModel));

    UserApiRepository userApiRepository = new UserApiRepository(userApi);
    TestObserver<User> testObserver = userApiRepository.getUser("jill").test();
    testObserver.awaitTerminalEvent();

    testObserver.assertValue(apiUser -> apiUser.getCreated() == 24268223);
  }
}
