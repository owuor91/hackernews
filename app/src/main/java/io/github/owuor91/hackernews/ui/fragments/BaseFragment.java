package io.github.owuor91.hackernews.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;
import io.github.owuor91.hackernews.di.fragment.FragmentComponent;
import io.github.owuor91.hackernews.di.fragment.FragmentModule;
import io.github.owuor91.hackernews.ui.activities.BaseActivity;
import io.github.owuor91.hackernews.ui.adapters.StoriesAdapter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by johnowuor on 21/03/2018.
 */

public class BaseFragment extends Fragment {
  private static final int NO_LAYOUT = -1;
  protected StoriesAdapter storiesAdapter;
  private CompositeDisposable compositeDisposable;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().baseInject(this);
  }

  public int getLayoutId() {
    return NO_LAYOUT;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (getLayoutId() != NO_LAYOUT) {
      View view = inflater.inflate(getLayoutId(), container, false);
      ButterKnife.bind(this, view);
      return view;
    } else {
      return super.onCreateView(inflater, container, savedInstanceState);
    }
  }

  public void handleError(Throwable throwable) {
    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
  }

  protected FragmentComponent injector() {
    return activityInjector().fragmentBuilder().fragmentModule(new FragmentModule(this)).build();
  }

  protected ActivityComponent activityInjector() {
    return ((BaseActivity) getActivity()).injector();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    dispose();
  }

  protected void setupStoriesAdapter(RecyclerView recyclerView) {
    if (storiesAdapter == null) {
      storiesAdapter = new StoriesAdapter(activityInjector(), getContext());
    }

    if (recyclerView.getAdapter() == null) {
      recyclerView.setAdapter(storiesAdapter);
    }
  }

  protected void dispose() {
    RxUtil.dispose(compositeDisposable);
  }
}
