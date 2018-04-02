package io.github.owuor91.presentation.home;

import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.repository.ItemRepository;
import io.github.owuor91.domain.repository.StoryRepository;
import io.github.owuor91.presentation.TrampolineSchedulerRule;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by johnowuor on 02/04/2018.
 */

@RunWith(MockitoJUnitRunner.class) public class ShowStoriesPresenterTest {
  @Rule public final TrampolineSchedulerRule trampolineSchedulerRule = new TrampolineSchedulerRule();

  @Mock ItemRepository itemApiRepository;
  @Mock StoryRepository storyApiRepository;
  @Mock StoryRepository storyDbRepository;
  @Mock ShowStoriesPresenter.View view;

  @Test public void shouldDisplayShowStories() {
    List<Story> storyList = new ArrayList<Story>();
    storyList.add(Story.newBuilder().withBy("me").withText("text").withId(23).build());

    Mockito.when(storyDbRepository.getStoriesList(Constants.SHOW_STORY)).thenReturn(Single.just(storyList));

    ShowStoriesPresenter showStoriesPresenter =
        new ShowStoriesPresenter(itemApiRepository, storyApiRepository, storyDbRepository);
    showStoriesPresenter.setView(view);
    showStoriesPresenter.getShowStories();

    Mockito.verify(view).showShowStories(storyList);
  }
}
