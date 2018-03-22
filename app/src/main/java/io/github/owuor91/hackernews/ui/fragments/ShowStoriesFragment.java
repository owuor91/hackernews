package io.github.owuor91.hackernews.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import io.github.owuor91.domain.models.Item;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.presentation.home.StoriesPresenter;
import java.util.List;
import javax.inject.Inject;

public class ShowStoriesFragment extends BaseFragment implements StoriesPresenter.ShowStoriesView {
  @BindView(R.id.showStoriesFragmentRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.showStoriesFragmentProgressBar) ProgressBar progressBar;

  @Inject StoriesPresenter storiesPresenter;

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
    storiesPresenter.setShowStoriesView(this);
    storiesPresenter.getShowStoryItems();
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showShowStoryItems(List<Item> itemList) {
    Toast.makeText(getContext(), itemList.size() + " show items found", Toast.LENGTH_LONG).show();
  }

  @Override protected void dispose() {
    super.dispose();
    storiesPresenter.dispose();
  }
}
