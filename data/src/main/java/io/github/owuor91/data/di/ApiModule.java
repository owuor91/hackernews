package io.github.owuor91.data.di;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import io.github.owuor91.data.BuildConfig;
import io.github.owuor91.data.api.HackerNewsApi;
import io.github.owuor91.domain.di.DIConstants;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johnowuor on 20/03/2018.
 */

@Module public class ApiModule {

  @Provides @Named(DIConstants.DEFAULT) public OkHttpClient provideDefaultOkHttpClient() {
    return new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .build();
  }


  @Provides @Named(DIConstants.DEFAULT)
  public Retrofit provideDefaultRetrofit(Gson gson, @Named(DIConstants.DEFAULT) OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build();
  }

  @Provides public HackerNewsApi provideHackerNewsApi(@Named(DIConstants.DEFAULT) Retrofit retrofit) {
    return retrofit.create(HackerNewsApi.class);
  }
}
