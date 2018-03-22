package io.github.owuor91.hackernews.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.hackernews.ui.adapters.StoriesAdapter;
import io.github.owuor91.presentation.home.AskStoriesPresenter;
import java.util.List;
import javax.inject.Inject;

public class AskStoriesFragment extends BaseFragment implements AskStoriesPresenter.View {
  @BindView(R.id.askStoriesFragmentRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.askStoriesFragmentProgressBar) ProgressBar progressBar;
  @Inject AskStoriesPresenter askStoriesPresenter;
  private StoriesAdapter storiesAdapter;

  public AskStoriesFragment() {
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_ask_stories;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  @Override public void onStart() {
    super.onStart();
    askStoriesPresenter.setView(this);
    askStoriesPresenter.getDbAskStories(); //getAskStories();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showAskStories(List<Story> askStoriesList) {
    if (storiesAdapter == null) {
      storiesAdapter = new StoriesAdapter(activityInjector());
    }

    if (recyclerView.getAdapter() == null) {
      recyclerView.setAdapter(storiesAdapter);
    }

    storiesAdapter.storiesAdapterPresenter.setAskStoriesPresenter(askStoriesPresenter);
    storiesAdapter.storiesAdapterPresenter.onDataChange(askStoriesList);
  }

  @Override protected void dispose() {
    super.dispose();
    askStoriesPresenter.dispose();
  }
}
