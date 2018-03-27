package io.github.owuor91.presentation.home;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.repository.ItemRepository;
import io.github.owuor91.domain.repository.StoryRepository;
import io.github.owuor91.presentation.BasePresenter;
import io.reactivex.Flowable;
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

public class AskStoriesPresenter implements BasePresenter {

  private CompositeDisposable compositeDisposable;
  private ItemRepository itemApiRepository;
  private StoryRepository storyApiRepository;
  private StoryRepository storyDbRepository;
  private View view;

  @Inject public AskStoriesPresenter(@Named(DIConstants.API) ItemRepository itemApiRepository,
      @Named(DIConstants.API) StoryRepository storyApiRepository,
      @Named(DIConstants.DB) StoryRepository storyDbRepository) {
    this.itemApiRepository = itemApiRepository;
    this.storyApiRepository = storyApiRepository;
    this.storyDbRepository = storyDbRepository;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void getAskStories() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    view.showProgress();

    Disposable disposable =
        storyDbRepository.getStoriesList(Constants.ASK_STORY)
            .subscribeOn(Schedulers.io())
            .flatMap(stories -> {
              if (stories.isEmpty()) {
                return getApiAskStories();
              } else {
                return Single.just(stories);
              }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(itemList -> view.hideProgress())
            .doOnError(throwable -> view.hideProgress())
            .subscribe(view::showAskStories, view::handleError);

    compositeDisposable.add(disposable);
  }

  private Single<List<Story>> getApiAskStories() {
    return itemApiRepository.getAskItems()
        .flatMapPublisher(Flowable::fromIterable)
        .flatMapSingle(item -> storyApiRepository.getStory(item))
        .toList();
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public interface View extends BasePresenter.View {
    void showProgress();

    void hideProgress();

    void showAskStories(List<Story> stories);
  }
}
