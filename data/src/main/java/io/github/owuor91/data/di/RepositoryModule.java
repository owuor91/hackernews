package io.github.owuor91.data.di;

import dagger.Module;
import dagger.Provides;
import io.github.owuor91.data.api.ItemsApi;
import io.github.owuor91.data.repository.item.ItemApiRepository;
import io.github.owuor91.data.repository.item.ItemDbRepository;
import io.github.owuor91.data.sql.dao.ItemDao;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.repository.ItemRepository;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module public class RepositoryModule {
  @Provides @Named(DIConstants.API) ItemRepository provideItemsApiRepository(ItemsApi itemsApi,
      @Named(DIConstants.DB) ItemRepository itemDbRepository) {
    return new ItemApiRepository(itemsApi, itemDbRepository);
  }

  @Provides @Named(DIConstants.DB) ItemRepository provideItemsDbRepository(ItemDao itemDao) {
    return new ItemDbRepository(itemDao);
  }
}
