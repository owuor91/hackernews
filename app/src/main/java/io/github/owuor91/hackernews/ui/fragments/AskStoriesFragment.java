package io.github.owuor91.hackernews.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.presentation.home.StoriesPresenter;
import java.util.List;
import javax.inject.Inject;

public class AskStoriesFragment extends BaseFragment implements StoriesPresenter.AskStoriesView {
  @BindView(R.id.askStoriesFragmentRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.askStoriesFragmentProgressBar) ProgressBar progressBar;
  @Inject StoriesPresenter storiesPresenter;

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
    storiesPresenter.setAskStoriesView(this);
    storiesPresenter.getAskStoryItems();
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showAskStories(List<Story> itemList) {
    Toast.makeText(getContext(), itemList.size() + " ask items found", Toast.LENGTH_LONG).show();
  }

  @Override protected void dispose() {
    super.dispose();
    storiesPresenter.dispose();
  }
}
