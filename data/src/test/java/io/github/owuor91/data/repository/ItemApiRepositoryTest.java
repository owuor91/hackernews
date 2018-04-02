package io.github.owuor91.data.repository;

import io.github.owuor91.data.api.ItemsApi;
import io.github.owuor91.data.repository.item.ItemApiRepository;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.repository.ItemRepository;
import io.reactivex.Flowable;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
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
  private List<Integer> items;

  @Before public void setup(){
    items = new ArrayList<Integer>();

    items.add(326712);
    items.add(463784);
    items.add(738910);
    items.add(328899);
    items.add(465782);
  }

  @Test public void shouldGetTopStoryItems() {

    Mockito.when(itemsApi.getTopStoryItems()).thenReturn(Flowable.just(items));

    ItemApiRepository itemApiRepository = new ItemApiRepository(itemsApi, itemDbRepository);
    TestObserver<List<Item>> testObserver = itemApiRepository.getTopItems().test();
    testObserver.awaitTerminalEvent();

    testObserver.assertValue(topItems -> topItems.size() == 5);
  }

  @Test public void shouldGetShowStoryItems() {

    Mockito.when(itemsApi.getShowStoryItems()).thenReturn(Flowable.just(items));

    ItemApiRepository itemApiRepository = new ItemApiRepository(itemsApi, itemDbRepository);
    TestObserver<List<Item>> testObserver = itemApiRepository.getShowItems().test();
    testObserver.awaitTerminalEvent();

    testObserver.assertValue(topItems -> topItems.size() == 5);
  }

  @Test public void shouldGetJobStoryItems() {
    Mockito.when(itemsApi.getJobStoryItems()).thenReturn(Flowable.just(items));

    ItemApiRepository itemApiRepository = new ItemApiRepository(itemsApi, itemDbRepository);
    TestObserver<List<Item>> testObserver = itemApiRepository.getJobItems().test();
    testObserver.awaitTerminalEvent();

    testObserver.assertValue(topItems -> topItems.size() == 5);
  }

  @Test public void shouldGetAskStoryItems() {

    Mockito.when(itemsApi.getAskStoryItems()).thenReturn(Flowable.just(items));

    ItemApiRepository itemApiRepository = new ItemApiRepository(itemsApi, itemDbRepository);
    TestObserver<List<Item>> testObserver = itemApiRepository.getAskItems().test();
    testObserver.awaitTerminalEvent();

    testObserver.assertValue(topItems -> topItems.size() == 5);
  }
}
