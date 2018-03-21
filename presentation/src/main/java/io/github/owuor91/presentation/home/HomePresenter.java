package io.github.owuor91.presentation.home;

import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.repository.ItemRepository;
import io.github.owuor91.presentation.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class HomePresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  private ItemRepository itemRepository;
  private View view;

  @Inject public HomePresenter(@Named(DIConstants.API) ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public void setView(View view) {
    this.view = view;
  }

  @Override public void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  public interface View extends BasePresenter.View {
  }
}
