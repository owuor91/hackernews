package io.github.owuor91.data.repository.story;

import io.github.owuor91.data.api.StoriesApi;
import io.github.owuor91.data.mappers.StoryMapper;
import io.github.owuor91.data.util.OperationImpossibleException;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.repository.StoryRepository;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Named;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoryApiRepository implements StoryRepository {
  private StoriesApi storiesApi;
  private StoryRepository storyDbRepository;

  public StoryApiRepository(StoriesApi storiesApi, @Named(DIConstants.DB) StoryRepository storyDbRepository) {
    this.storiesApi = storiesApi;
    this.storyDbRepository = storyDbRepository;
  }

  @Override public Single<Story> getStory(Item item) {
    return storiesApi.getStory(item.getItem()).map(StoryMapper::transformFromApi).map(story -> {
      story.setItemType(item.getItemType());
      return story;
    }).flatMapSingle(storyDbRepository::saveStory).firstOrError();
  }

  @Override public Single<Story> saveStory(Story story) {
    return Single.error(new OperationImpossibleException());
  }

  @Override public Single<List<Story>> getStoriesList(String itemType) {
    return Single.error(new OperationImpossibleException());
  }

  @Override public Single<Integer> deleteStories() {
    return Single.error(new OperationImpossibleException());
  }
}
