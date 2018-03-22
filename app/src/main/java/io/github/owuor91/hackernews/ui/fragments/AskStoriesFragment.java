package io.github.owuor91.hackernews.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import butterknife.BindView;
import io.github.owuor91.hackernews.R;

public class AskStoriesFragment extends BaseFragment {
  @BindView(R.id.askStoriesFragmentRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.askStoriesFragmentProgressBar) ProgressBar progressBar;

  public AskStoriesFragment() {
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_ask_stories;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }
}
