package io.github.owuor91.presentation.home;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.repository.ItemRepository;
import io.github.owuor91.domain.repository.StoryRepository;
import io.github.owuor91.presentation.BasePresenter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class ShowStoriesPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  private ItemRepository itemApiRepository;
  private ItemRepository itemDbRepository;
  private StoryRepository storyApiRepository;
  private StoryRepository storyDbRepository;
  private View view;

  @Inject public ShowStoriesPresenter(@Named(DIConstants.API) ItemRepository itemApiRepository,
      @Named(DIConstants.DB) ItemRepository itemDbRepository,
      @Named(DIConstants.API) StoryRepository storyApiRepository,
      @Named(DIConstants.DB) StoryRepository storyDbRepository) {
    this.itemApiRepository = itemApiRepository;
    this.itemDbRepository = itemDbRepository;
    this.storyApiRepository = storyApiRepository;
    this.storyDbRepository = storyDbRepository;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void getShowStoryItems() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    view.showProgress();

    Disposable disposable = itemDbRepository.getShowItems()
        .subscribeOn(Schedulers.io())
        .flatMap(itemList -> {
          if (itemList.isEmpty()) {
            return itemApiRepository.getShowItems();
          } else {
            return Single.just(itemList);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(itemList -> view.hideProgress())
        .doOnError(throwable -> view.hideProgress())
        .subscribe(view::showShowStoryItems, view::handleError);

    compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public interface View extends BasePresenter.View {
    void showProgress();

    void hideProgress();

    void showShowStoryItems(List<Item> itemList);
  }
}
