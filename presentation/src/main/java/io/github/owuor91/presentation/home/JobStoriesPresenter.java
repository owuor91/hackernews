package io.github.owuor91.presentation.home;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Item;
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

public class JobStoriesPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  private ItemRepository itemApiRepository;
  private ItemRepository itemDbRepository;
  private StoryRepository storyApiRepository;
  private StoryRepository storyDbRepository;
  private View view;

  @Inject public JobStoriesPresenter(@Named(DIConstants.API) ItemRepository itemApiRepository,
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

  public void getJobStories() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    view.showProgress();

    Disposable disposable = getJobItems().subscribeOn(Schedulers.io())
        .flatMapPublisher(Flowable::fromIterable)
        .flatMapSingle(item -> storyApiRepository.getStory(item))
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(itemList -> view.hideProgress())
        .doOnError(throwable -> view.hideProgress())
        .subscribe(view::showJobStories, view::handleError);

    compositeDisposable.add(disposable);
  }

  public void getDbJobStories() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    view.showProgress();

    Disposable disposable = storyDbRepository.getStoriesList(Constants.JOB_STORY)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(stories -> view.hideProgress())
        .doOnError(throwable -> view.hideProgress())
        .subscribe(view::showJobStories, view::handleError);

    compositeDisposable.add(disposable);
  }

  private Single<List<Item>> getJobItems() {
    return itemDbRepository.getJobItems().flatMap(itemList -> {
      if (itemList.isEmpty()) {
        return itemApiRepository.getJobItems();
      } else {
        return Single.just(itemList);
      }
    });
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public interface View extends BasePresenter.View {
    void showProgress();

    void hideProgress();

    void showJobStories(List<Story> jobStoriesList);
  }
}
