package io.github.owuor91.presentation.home;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.domain.repository.ItemRepository;
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

public class StoriesPresenter implements BasePresenter {

  private TopStoriesView topStoriesView;
  private ShowStoriesView showStoriesView;
  private JobStoriesView jobStoriesView;
  private AskStoriesView askStoriesView;
  private CompositeDisposable compositeDisposable;
  private ItemRepository itemApiRepository;
  private ItemRepository itemDbRepository;

  @Inject public StoriesPresenter(@Named(DIConstants.API) ItemRepository itemApiRepository,
      @Named(DIConstants.DB) ItemRepository itemDbRepository) {
    this.itemApiRepository = itemApiRepository;
    this.itemDbRepository = itemDbRepository;
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

    Disposable disposable = itemDbRepository.getTopStories()
        .subscribeOn(Schedulers.io())
        .flatMap(itemList -> {
          if (itemList.isEmpty()) {
            return itemApiRepository.getTopStories();
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

    Disposable disposable = itemDbRepository.getShowStories()
        .subscribeOn(Schedulers.io())
        .flatMap(itemList -> {
          if (itemList.isEmpty()) {
            return itemApiRepository.getShowStories();
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

    Disposable disposable = itemDbRepository.getJobStories()
        .subscribeOn(Schedulers.io())
        .flatMap(itemList -> {
          if (itemList.isEmpty()) {
            return itemApiRepository.getJobStories();
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

    Disposable disposable = itemDbRepository.getAskStories()
        .subscribeOn(Schedulers.io())
        .flatMap(itemList -> {
          if (itemList.isEmpty()) {
            return itemApiRepository.getAskStories();
          } else {
            return Single.just(itemList);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(itemList -> askStoriesView.hideProgress())
        .doOnError(throwable -> askStoriesView.hideProgress())
        .subscribe(askStoriesView::showAskStoryItems, askStoriesView::handleError);

    compositeDisposable.add(disposable);
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

    void showAskStoryItems(List<Item> itemList);
  }
}
