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
import io.github.owuor91.presentation.home.TopStoriesPresenter;
import java.util.List;
import javax.inject.Inject;

public class TopStoriesFragment extends BaseFragment implements TopStoriesPresenter.View {
  @BindView(R.id.topStoriesFragmentRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.topStoriesFragmentProgressBar) ProgressBar progressBar;

  @Inject TopStoriesPresenter topStoriesPresenter;

  public TopStoriesFragment() {
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_top_stories;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  @Override public void onStart() {
    super.onStart();
    topStoriesPresenter.setView(this);
    topStoriesPresenter.getTopStoryItems();
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showTopStoryItems(List<Item> itemList) {
    Toast.makeText(getContext(), itemList.size() + " top items found", Toast.LENGTH_LONG).show();
  }

  @Override protected void dispose() {
    super.dispose();
    topStoriesPresenter.dispose();
  }
}
