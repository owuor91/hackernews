package io.github.owuor91.hackernews.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.domain.utils.DateTimeUtils;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.hackernews.ui.adapters.StoriesAdapter;
import io.github.owuor91.hackernews.ui.viewutils.TextLinkUtil;
import io.github.owuor91.presentation.comments.CommentsPresenter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;

public class CommentsActivity extends BaseActivity implements CommentsPresenter.View {

  @BindView(R.id.commentsActivityToolbar) Toolbar toolbar;
  @BindView(R.id.commentsActivityTvToolbar) TextView tvToolbar;
  @BindView(R.id.commentsActivityProgressBar) ProgressBar progressBar;
  @BindView(R.id.commentsActivityTvBy) TextView tvBy;
  @BindView(R.id.commentsActivityTvTitle) TextView tvTitle;
  @BindView(R.id.commentsActivityTvText) TextView tvText;
  @BindView(R.id.commentsActivityTvUrl) TextView tvUrl;
  @BindView(R.id.commentsActivityRecyclerView) RecyclerView recyclerView;
  @BindView(R.id.commentsActivityTvTime) TextView tvTime;
  @Inject CommentsPresenter commentsPresenter;
  private StoriesAdapter storiesAdapter;
  private int storyId;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comments);
    injector().inject(this);

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      storyId = bundle.getInt(Constants.STORY_ID);
    }
  }

  @Override protected void onStart() {
    super.onStart();
    commentsPresenter.setView(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

    commentsPresenter.getStoryById(storyId);
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void displayStory(Story story) {
    tvBy.setText(story.getBy());
    if (!TextUtils.isEmpty(story.getText())) {
      TextLinkUtil.setText(story.getText(), tvText, getBaseContext());
    }

    tvTime.setText(DateTimeUtils.getElapsedTime(story.getTime()));

    tvTitle.setText(story.getTitle());

    if (story.getUrl() != null && !TextUtils.isEmpty(story.getUrl())) {
      tvUrl.setText(story.getUrl());
      tvUrl.setOnClickListener(view -> {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(story.getUrl())));
      });
    } else {
      hideUrlTextView();
    }

    commentsPresenter.getCommentsList(story.getKids());
  }

  @Override public void displayComments(List<Story> commentsList) {
    Collections.sort(commentsList, new Comparator<Story>() {
      @Override public int compare(Story story, Story t1) {
        return t1.getScore() - story.getScore();
      }
    });
    if (storiesAdapter == null) {
      storiesAdapter = new StoriesAdapter(injector(), getBaseContext());
    }

    if (recyclerView.getAdapter() == null) {
      recyclerView.setAdapter(storiesAdapter);
    }

    storiesAdapter.storiesAdapterPresenter.setCommentsPresenter(commentsPresenter);
    storiesAdapter.storiesAdapterPresenter.onDataChange(commentsList);
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  @Override public void hideUrlTextView() {
    tvUrl.setVisibility(View.GONE);
  }
}
