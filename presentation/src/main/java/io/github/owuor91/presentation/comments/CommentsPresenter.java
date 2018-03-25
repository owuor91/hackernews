package io.github.owuor91.presentation.comments;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Story;
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
 * Created by johnowuor on 25/03/2018.
 */

public class CommentsPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  private View view;
  private StoryRepository storyApiRepository;

  @Inject public CommentsPresenter(@Named(DIConstants.API) StoryRepository storyApiRepository) {
    this.storyApiRepository = storyApiRepository;
  }

  public void setView(View view) {
    this.view = view;
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public void getStoryById(int storyId) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    view.showProgress();

    Disposable disposable = storyApiRepository.getStoryById(storyId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(story -> view.hideProgress())
        .doOnError(throwable -> view.hideProgress())
        .subscribe(view::displayStory, view::handleError);

    compositeDisposable.add(disposable);
  }

  public void getCommentsList(List<Integer> commentIds) {
    if (commentIds == null || commentIds.isEmpty()) {
      return;
    }
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    view.showProgress();

    Disposable disposable = Single.just(commentIds)
        .subscribeOn(Schedulers.io())
        .flatMapPublisher(Flowable::fromIterable)
        .flatMapSingle(storyApiRepository::getStoryById)
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(story -> view.hideProgress())
        .doOnError(throwable -> view.hideProgress())
        .subscribe(view::displayComments, view::handleError);

    compositeDisposable.add(disposable);
  }

  public interface View extends BasePresenter.View {
    void showProgress();

    void hideProgress();

    void displayStory(Story story);

    void displayComments(List<Story> commentsList);

    void hideUrlTextView();
  }
}
