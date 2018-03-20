package io.github.owuor91.presentation.home;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Article;
import io.github.owuor91.domain.repository.ArticleRepository;
import io.github.owuor91.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class HomePresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  private ArticleRepository articleRepository;
  private View view;

  @Inject public HomePresenter(@Named(DIConstants.API) ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void getArticles(){
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);

    Disposable disposable = articleRepository.getArticles()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::showArticles, view::handleError);

    compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public interface View extends BasePresenter.View{
    void showArticles(List<Article> articleList);
  }
}
