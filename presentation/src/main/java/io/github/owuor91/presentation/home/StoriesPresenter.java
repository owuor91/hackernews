package io.github.owuor91.presentation.home;

import io.github.owuor91.data.util.RxUtil;
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

public class StoriesPresenter implements BasePresenter {

  private TopStoriesView topStoriesView;
  private ShowStoriesView showStoriesView;
  private JobStoriesView jobStoriesView;
  private AskStoriesView askStoriesView;
  private CompositeDisposable compositeDisposable;
  private ItemRepository itemApiRepository;
  private ItemRepository itemDbRepository;
  private StoryRepository storyApiRepository;
  private StoryRepository storyDbRepository;

  @Inject public StoriesPresenter(@Named(DIConstants.API) ItemRepository itemApiRepository,
      @Named(DIConstants.DB) ItemRepository itemDbRepository,
      @Named(DIConstants.API) StoryRepository storyApiRepository,
      @Named(DIConstants.DB) StoryRepository storyDbRepository) {
    this.itemApiRepository = itemApiRepository;
    this.itemDbRepository = itemDbRepository;
    this.storyApiRepository = storyApiRepository;
    this.storyDbRepository = storyDbRepository;
  }

  public void setTopStoriesView(TopStoriesView topStoriesView) {
    this.topStoriesView = topStoriesView;
  }

  public void setShowStoriesView(ShowStoriesView showStoriesView) {
    this.showStoriesView = showStoriesView;
  }

  public void setJobStoriesView(JobStoriesView jobStoriesView) {
    this.jobStoriesView = jobStoriesView;
  }

  public void setAskStoriesView(AskStoriesView askStoriesView) {
    this.askStoriesView = askStoriesView;
  }

  public void getTopStoryItems() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    topStoriesView.showProgress();

    Disposable disposable = itemDbRepository.getTopItems()
        .subscribeOn(Schedulers.io())
        .flatMap(itemList -> {
          if (itemList.isEmpty()) {
            return itemApiRepository.getTopItems();
          } else {
            return Single.just(itemList);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(itemList -> topStoriesView.hideProgress())
        .doOnError(throwable -> topStoriesView.hideProgress())
        .subscribe(topStoriesView::showTopStoryItems, topStoriesView::handleError);

    compositeDisposable.add(disposable);
  }

  public void getShowStoryItems() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    showStoriesView.showProgress();

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
        .doOnSuccess(itemList -> showStoriesView.hideProgress())
        .doOnError(throwable -> showStoriesView.hideProgress())
        .subscribe(showStoriesView::showShowStoryItems, showStoriesView::handleError);

    compositeDisposable.add(disposable);
  }

  public void getJobStoryItems() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    jobStoriesView.showProgress();

    Disposable disposable = itemDbRepository.getJobItems()
        .subscribeOn(Schedulers.io())
        .flatMap(itemList -> {
          if (itemList.isEmpty()) {
            return itemApiRepository.getJobItems();
          } else {
            return Single.just(itemList);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(itemList -> jobStoriesView.hideProgress())
        .doOnError(throwable -> jobStoriesView.hideProgress())
        .subscribe(jobStoriesView::showJobStoryItems, jobStoriesView::handleError);

    compositeDisposable.add(disposable);
  }

  public void getAskStoryItems() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    askStoriesView.showProgress();

    Disposable disposable = getAskItems()
        .subscribeOn(Schedulers.io())
        .flatMapPublisher(Flowable::fromIterable)
        .flatMapSingle(item -> storyApiRepository.getStory(item))
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(stories -> askStoriesView.hideProgress())
        .doOnError(throwable -> askStoriesView.hideProgress())
        .subscribe(askStoriesView::showAskStories, askStoriesView::handleError);

    compositeDisposable.add(disposable);
  }

  private Single<List<Item>> getAskItems() {
    return itemDbRepository.getAskItems().flatMap(itemList -> {
      if (itemList.isEmpty()) {
        return itemApiRepository.getAskItems();
      } else {
        return Single.just(itemList);
      }
    });
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public interface TopStoriesView extends BasePresenter.View {
    void showProgress();

    void hideProgress();

    void showTopStoryItems(List<Item> itemList);
  }

  public interface ShowStoriesView extends BasePresenter.View {
    void showProgress();

    void hideProgress();

    void showShowStoryItems(List<Item> itemList);
  }

  public interface JobStoriesView extends BasePresenter.View {
    void showProgress();

    void hideProgress();

    void showJobStoryItems(List<Item> itemList);
  }

  public interface AskStoriesView extends BasePresenter.View {
    void showProgress();

    void hideProgress();

    void showAskStories(List<Story> stories);
  }
}
