package io.github.owuor91.domain.repository;

import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.models.Story;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 22/03/2018.
 */

public interface StoryRepository {
  Single<Story> getStory(Item item);

  Single<Story> saveStory(Story story);

  Single<List<Story>> getStoriesList(String itemType);

  Single<Integer> deleteStories();
}
