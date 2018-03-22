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

public class JobStoriesFragment extends BaseFragment implements StoriesPresenter.JobStoriesView {
  @BindView(R.id.jobStoriesFragmentRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.jobStoriesFragmentProgressBar) ProgressBar progressBar;

  @Inject StoriesPresenter storiesPresenter;

  public JobStoriesFragment() {
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_job_stories;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  @Override public void onStart() {
    super.onStart();
    storiesPresenter.setJobStoriesView(this);
    storiesPresenter.getJobStoryItems();
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showJobStoryItems(List<Item> itemList) {
    Toast.makeText(getContext(), itemList.size() + " job items found", Toast.LENGTH_LONG).show();
  }

  @Override protected void dispose() {
    super.dispose();
    storiesPresenter.dispose();
  }
}
