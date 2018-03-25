package io.github.owuor91.data.repository.story;

import io.github.owuor91.data.mappers.StoryMapper;
import io.github.owuor91.data.sql.dao.StoryDao;
import io.github.owuor91.data.util.OperationImpossibleException;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.repository.StoryRepository;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoryDbRepository implements StoryRepository {
  private StoryDao storyDao;

  public StoryDbRepository(StoryDao storyDao) {
    this.storyDao = storyDao;
  }

  @Override public Single<Story> getStory(Item item) {
    return Single.error(new OperationImpossibleException());
  }

  @Override public Single<Story> saveStory(Story story) {
    return Single.just(story).map(StoryMapper::transformToDb).map(storyDao::insertStory).map(l -> story);
  }

  @Override public Single<List<Story>> getStoriesList(String itemType) {
    return storyDao.getStoriesList(itemType)
        .flatMapPublisher(Flowable::fromIterable)
        .map(StoryMapper::transformFromDb)
        .toList();
  }

  @Override public Single<Integer> deleteStories() {
    return storyDao.getAllStories().map(storyDao::deleteStories);
  }

  @Override public Single<Story> getStoryById(int storyId) {
    return Single.error(new OperationImpossibleException());
  }
}
