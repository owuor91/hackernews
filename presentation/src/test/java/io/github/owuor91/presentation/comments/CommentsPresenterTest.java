package io.github.owuor91.presentation.comments;

import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.repository.StoryRepository;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by johnowuor on 26/03/2018.
 */

@RunWith(MockitoJUnitRunner.class) public class CommentsPresenterTest {

  @Test public void shouldDisplayStory() {
    List<Integer> kidsList = new ArrayList<Integer>();
    kidsList.add(57380);
    kidsList.add(78473);
    kidsList.add(74628);

    Story apiStory = Story.newBuilder()
        .withBy("userX")
        .withDescendants(23)
        .withId(47229)
        .withItemType("itemType")
        .withKids(kidsList)
        .withScore(91)
        .withText("storyText")
        .withTime(743862)
        .withTitle("storyTitle")
        .withUrl("https://story.url.de")
        .withType("story")
        .build();

    StoryRepository storyApiRepository = Mockito.mock(StoryRepository.class);
    CommentsPresenter.View view = Mockito.mock(CommentsPresenter.View.class);

    Mockito.when(storyApiRepository.getStoryById(47229)).thenReturn(Single.just(apiStory));
    CommentsPresenter commentsPresenter = new CommentsPresenter(storyApiRepository);
    commentsPresenter.setView(view);
    commentsPresenter.getStoryById(47229);

    Mockito.verify(view).displayStory(apiStory);
  }
}
