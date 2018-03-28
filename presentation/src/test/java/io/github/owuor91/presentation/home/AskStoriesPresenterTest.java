package io.github.owuor91.presentation.home;

import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.repository.ItemRepository;
import io.github.owuor91.domain.repository.StoryRepository;
import io.github.owuor91.presentation.TrampolineSchedulerRule;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by johnowuor on 28/03/2018.
 */

@RunWith(MockitoJUnitRunner.class) public class AskStoriesPresenterTest {

  @Rule public final TrampolineSchedulerRule trampolineSchedulerRule = new TrampolineSchedulerRule();

  @Mock ItemRepository itemApiRepository;
  @Mock StoryRepository storyApiRepository;
  @Mock StoryRepository storyDbRepository;
  @Mock AskStoriesPresenter.View view;

  @Test public void shouldShowAskStories() {
    List<Story> storyList = new ArrayList<Story>();
    storyList.add(Story.newBuilder().withBy("me").withText("text").withId(23).build());
    Mockito.when(storyDbRepository.getStoriesList(Constants.ASK_STORY)).thenReturn(Single.just(storyList));
    AskStoriesPresenter askStoriesPresenter =
        new AskStoriesPresenter(itemApiRepository, storyApiRepository, storyDbRepository);
    askStoriesPresenter.setView(view);
    askStoriesPresenter.getAskStories();

    Mockito.verify(view).showAskStories(storyList);
  }

  @Test public void shouldgetApiStories() {
    List<Item> askItemsList = new ArrayList<Item>();
    askItemsList.add(Item.newBuilder().withId(1).withItem(100).withItemType(Constants.ASK_STORY).build());
    askItemsList.add(Item.newBuilder().withId(2).withItem(200).withItemType(Constants.ASK_STORY).build());

    Mockito.when(itemApiRepository.getAskItems()).thenReturn(Single.just(askItemsList));
    Mockito.when(storyApiRepository.getStoryById(100)).thenReturn(Single.just(Story.newBuilder().withId(100).build()));
    Mockito.when(storyApiRepository.getStoryById(200)).thenReturn(Single.just(Story.newBuilder().withId(200).build()));

    AskStoriesPresenter askStoriesPresenter =
        new AskStoriesPresenter(itemApiRepository, storyApiRepository, storyDbRepository);
    TestObserver<List<Story>> testObserver = askStoriesPresenter.getApiAskStories().test();
    testObserver.awaitTerminalEvent();

    testObserver.assertValue(stories -> stories.size() == 2);
  }
}
