package io.github.owuor91.data.repository;

import io.github.owuor91.data.api.StoriesApi;
import io.github.owuor91.data.models.api.StoryApiModel;
import io.github.owuor91.data.repository.story.StoryApiRepository;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.repository.StoryRepository;
import io.reactivex.Flowable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by johnowuor on 26/03/2018.
 */

@RunWith(MockitoJUnitRunner.class) public class StoryApiRepositoryTest {
  @Mock StoriesApi storiesApi;
  @Mock StoryRepository storyDbRepository;

  @Test public void shouldGetStoryById() {
    StoryApiModel storyApiModel = StoryApiModel.newBuilder()
        .withBy("gerryblossom")
        .withDescendants(84)
        .withId(292849)
        .withScore(43)
        .withTime(7548393)
        .withTitle("divine sorrow")
        .withType("Story")
        .withUrl("https://stories.de.url")
        .build();

    Mockito.when(storiesApi.getStory(24811)).thenReturn(Flowable.just(storyApiModel));

    StoryApiRepository storyApiRepository = new StoryApiRepository(storiesApi, storyDbRepository);
    TestObserver<Story> testObserver = storyApiRepository.getStoryById(24811).test();
    testObserver.awaitTerminalEvent();

    testObserver.assertValue(apiStory -> apiStory.getBy().equals("gerryblossom"));
  }
}
