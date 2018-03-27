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
import io.github.owuor91.presentation.home.ShowStoriesPresenter;
import java.util.List;
import javax.inject.Inject;

public class ShowStoriesFragment extends BaseFragment implements ShowStoriesPresenter.View {
  @BindView(R.id.showStoriesFragmentRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.showStoriesFragmentProgressBar) ProgressBar progressBar;
  @Inject ShowStoriesPresenter showStoriesPresenter;

  public ShowStoriesFragment() {
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_show_stories;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  @Override public void onStart() {
    super.onStart();
    showStoriesPresenter.setView(this);
  }

  @Override public void onResume() {
    super.onResume();
    showStoriesPresenter.getShowStories();
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

  @Override public void showShowStories(List<Story> showStoriesList) {
    setupStoriesAdapter(recyclerView);

    storiesAdapter.storiesAdapterPresenter.setShowStoriesPresenter(showStoriesPresenter);
    storiesAdapter.storiesAdapterPresenter.onDataChange(showStoriesList);
  }

  @Override protected void dispose() {
    super.dispose();
    showStoriesPresenter.dispose();
  }
}
