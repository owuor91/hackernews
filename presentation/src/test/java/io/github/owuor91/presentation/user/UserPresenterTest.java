package io.github.owuor91.presentation.user;

import io.github.owuor91.domain.models.User;
import io.github.owuor91.domain.repository.UserRepository;
import io.github.owuor91.presentation.TrampolineSchedulerRule;
import io.reactivex.Single;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by johnowuor on 02/04/2018.
 */

@RunWith(MockitoJUnitRunner.class) public class UserPresenterTest {
  @Rule public TrampolineSchedulerRule trampolineSchedulerRule = new TrampolineSchedulerRule();
  @Mock UserRepository userApiRepository;
  @Mock UserPresenter.View view;

  @Test public void shouldDisplayUser() {
    User user = User.newBuilder().withKarma(23).withDelay(2).withAbout("About").withUserId("userId").build();

    Mockito.when(userApiRepository.getUser("userId")).thenReturn(Single.just(user));
    UserPresenter userPresenter = new UserPresenter(userApiRepository);
    userPresenter.setView(view);
    userPresenter.getUserDetails("userId");

    Mockito.verify(view).displayUser(user);
  }
}
