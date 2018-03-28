package io.github.owuor91.presentation.comments;

import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.repository.StoryRepository;
import io.github.owuor91.presentation.TrampolineSchedulerRule;
import io.reactivex.Single;
import java.util.NoSuchElementException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;

/**
 * Created by johnowuor on 26/03/2018.
 */

@RunWith(MockitoJUnitRunner.class) public class CommentsPresenterTest {
  @Rule public TrampolineSchedulerRule trampolineSchedulerRule = new TrampolineSchedulerRule();

  @Mock StoryRepository storyApiRepository;
  @Mock CommentsPresenter.View view;

  @Test public void shouldDisplayStory() {
    Story apiStory = Story.newBuilder().build();

    Mockito.when(storyApiRepository.getStoryById(anyInt())).thenReturn(Single.just(apiStory));
    CommentsPresenter commentsPresenter = new CommentsPresenter(storyApiRepository);
    commentsPresenter.setView(view);
    commentsPresenter.getStoryById(67);

    Mockito.verify(view).displayStory(apiStory);
  }

  @Test public void shouldFailWithInvalidStoryId() {
    Mockito.when(storyApiRepository.getStoryById(anyInt())).thenReturn(Single.error(new NoSuchElementException()));
    CommentsPresenter commentsPresenter = new CommentsPresenter(storyApiRepository);
    commentsPresenter.setView(view);
    commentsPresenter.getStoryById(0);

    Throwable throwable = new Throwable();

    Mockito.verify(view).handleError(new NoSuchElementException());
  }
}
