package io.github.owuor91.domain.repository;

import io.reactivex.Single;
import java.util.List;

/**
 * Created by johnowuor on 20/03/2018.
 */

public interface ItemRepository {
  Single<List<Integer>> getTopStories();

  Single<List<Integer>> getShowStories();

  Single<List<Integer>> getJobStories();
}
