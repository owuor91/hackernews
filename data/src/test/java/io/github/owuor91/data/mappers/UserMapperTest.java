package io.github.owuor91.data.mappers;

import io.github.owuor91.data.models.api.UserApiModel;
import io.github.owuor91.domain.models.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by johnowuor on 25/03/2018.
 */

@RunWith(JUnit4.class) public class UserMapperTest {
  private UserApiModel userApiModel;
  private User user;
  private List<Integer> submissions = new ArrayList<Integer>();

  @Before public void setup() {
    userApiModel = userApiModel.newBuilder()
        .withUserId("userx")
        .withAbout("about userx")
        .withCreated(43784)
        .withDelay(6)
        .withKarma(893)
        .withSubmitted(submissions)
        .build();

    user = user.newBuilder()
        .withUserId("userx")
        .withAbout("about userx")
        .withCreated(43784)
        .withDelay(6)
        .withKarma(893)
        .withSubmitted(submissions)
        .build();
  }

  @Test public void shouldConvertUserApiModelToUser() {
    User myUser = UserMapper.transformFromApi(userApiModel);

    Assert.assertEquals(myUser.getUserId(), userApiModel.getUserId());
    Assert.assertEquals(myUser.getAbout(), userApiModel.getAbout());
    Assert.assertEquals(myUser.getCreated(), userApiModel.getCreated());
    Assert.assertEquals(myUser.getDelay(), userApiModel.getDelay());
    Assert.assertEquals(myUser.getKarma(), userApiModel.getKarma());
    Assert.assertEquals(myUser.getSubmitted(), userApiModel.getSubmitted());
  }
}
