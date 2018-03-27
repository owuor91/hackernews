package io.github.owuor91.presentation.home;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.repository.StoryRepository;
import io.github.owuor91.presentation.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class HomePresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  private StoryRepository storyDbRepository;
  private View view;

  @Inject public HomePresenter(@Named(DIConstants.DB) StoryRepository storyDbRepository) {
    this.storyDbRepository = storyDbRepository;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void deleteDbStories() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);

    Disposable disposable = storyDbRepository.deleteStories().subscribeOn(Schedulers.io()).subscribe();

    compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public interface View extends BasePresenter.View {
  }
}
