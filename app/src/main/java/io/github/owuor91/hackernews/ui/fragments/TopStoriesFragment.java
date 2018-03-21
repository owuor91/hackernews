package io.github.owuor91.hackernews.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import butterknife.BindView;
import io.github.owuor91.hackernews.R;

public class TopStoriesFragment extends BaseFragment {
  @BindView(R.id.topStoriesFragmentRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.topStoriesFragmentProgressBar) ProgressBar progressBar;

  public TopStoriesFragment() {
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_top_stories;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }
}
