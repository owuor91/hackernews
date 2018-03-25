package io.github.owuor91.presentation.user;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.User;
import io.github.owuor91.domain.repository.UserRepository;
import io.github.owuor91.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johnowuor on 25/03/2018.
 */

public class UserPresenter implements BasePresenter {
  CompositeDisposable compositeDisposable;
  private View view;
  private UserRepository userApiRepository;

  @Inject public UserPresenter(@Named(DIConstants.API) UserRepository userApiRepository) {
    this.userApiRepository = userApiRepository;
  }

  public void getUserDetails(String userId) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    view.showProgress();

    Disposable disposable = userApiRepository.getUser(userId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError(throwable -> view.hideProgress())
        .doOnSuccess(user -> view.hideProgress())
        .subscribe(view::displayUser, view::handleError);

    compositeDisposable.add(disposable);
  }

  public void setView(View view) {
    this.view = view;
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public interface View extends BasePresenter.View {

    void showProgress();

    void hideProgress();

    void displayUser(User user);
  }
}
