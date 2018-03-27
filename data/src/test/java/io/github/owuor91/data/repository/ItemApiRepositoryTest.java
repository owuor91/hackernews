package io.github.owuor91.data.repository;

import io.github.owuor91.data.api.ItemsApi;
import io.github.owuor91.data.repository.item.ItemApiRepository;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.repository.ItemRepository;
import io.reactivex.Flowable;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by johnowuor on 26/03/2018.
 */

@RunWith(MockitoJUnitRunner.class) public class ItemApiRepositoryTest {
  @Mock ItemsApi itemsApi;
  @Mock ItemRepository itemDbRepository;

  @Test public void shouldGetTopStoryItems() {
    List<Integer> topStoryItems = new ArrayList<Integer>();

    topStoryItems.add(326712);
    topStoryItems.add(463784);
    topStoryItems.add(738910);
    topStoryItems.add(328899);
    topStoryItems.add(465782);

    Mockito.when(itemsApi.getTopStoryItems()).thenReturn(Flowable.just(topStoryItems));

    ItemApiRepository itemApiRepository = new ItemApiRepository(itemsApi, itemDbRepository);
    TestObserver<List<Item>> testObserver = itemApiRepository.getTopItems().test();
    testObserver.awaitTerminalEvent();

    testObserver.assertValue(topItems -> topItems.size() == 5);
  }
}
