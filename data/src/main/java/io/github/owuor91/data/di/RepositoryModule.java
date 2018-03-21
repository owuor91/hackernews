package io.github.owuor91.data.di;

import dagger.Module;
import dagger.Provides;
import io.github.owuor91.data.api.ItemsApi;
import io.github.owuor91.data.repository.article.ItemApiRepository;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.repository.ItemRepository;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module public class RepositoryModule {
  @Provides @Named(DIConstants.API) ItemRepository provideItemsApiRepository(ItemsApi itemsApi) {
    return new ItemApiRepository(itemsApi);
  }
}
