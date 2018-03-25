package io.github.owuor91.hackernews.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.hackernews.di.adapter.AdapterComponent;
import io.github.owuor91.hackernews.ui.activities.CommentsActivity;
import io.github.owuor91.hackernews.ui.activities.UserActivity;
import io.github.owuor91.hackernews.ui.viewutils.TextLinkUtil;
import io.github.owuor91.presentation.home.StoriesViewHolderPresenter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoriesViewHolder extends BaseViewHolder implements StoriesViewHolderPresenter.View {
  public static final int LAYOUT_ID = R.layout.story_list_item;
  public static final String SINGLE_COMMENT = "comment";

  @Inject public StoriesViewHolderPresenter storiesViewHolderPresenter;
  @Inject @Named(DIConstants.APP) Context context;

  @BindView(R.id.storyListItemTvBy) TextView tvBy;
  @BindView(R.id.storyListItemTvTitle) TextView tvTitle;
  @BindView(R.id.storyListItemTvText) TextView tvText;
  @BindView(R.id.storyListItemTvUrl) TextView tvUrl;
  @BindView(R.id.storyListItemTvComments) TextView tvComments;
  @BindView(R.id.storyListItemTvReadMore) TextView tvReadMore;
  @BindView(R.id.storyListItemTvScore) TextView tvScore;
  @BindView(R.id.storyListItemImgAvatar) ImageView imgAvatar;

  public StoriesViewHolder(View itemView, AdapterComponent adapterComponent) {
    super(itemView);
    adapterComponent.inject(this);
    storiesViewHolderPresenter.setView(this);
  }

  @Override public void handleError(Throwable throwable) {

  }

  @Override public void setBy(String by) {
    tvBy.setText(by);
  }

  @Override public void setTitle(String title) {
    tvTitle.setText(title);
  }

  @Override public void setText(String text) {
    TextLinkUtil.setText(text, tvText, context);
  }

  @Override public void hideStoryTextView() {
    tvText.setVisibility(View.GONE);
  }

  @Override public void setComments(int commentsCount) {
    tvComments.setText(String.valueOf(commentsCount));
  }

  @Override public void setUrl(String url) {
    tvUrl.setText(url);
  }

  @Override public void hideUrlView() {
    tvUrl.setVisibility(View.GONE);
  }

  @OnClick(R.id.storyListItemTvUrl) public void openLink() {
    storiesViewHolderPresenter.onClickLink();
  }

  @OnClick(R.id.storyListItemTvReadMore) public void clickReadmore() {
    storiesViewHolderPresenter.onClickReadMore();
  }

  @Override public void showReadMoreLink() {
    tvReadMore.setVisibility(View.VISIBLE);
  }

  @Override public void setScore(int score) {
    tvScore.setText(String.valueOf(score));
  }

  @Override public void hideReadMoreLink() {
    tvReadMore.setVisibility(View.GONE);
  }

  @OnClick(R.id.storyListItemTvComments) public void onCommentClick() {
    storiesViewHolderPresenter.onCommentClick();
  }

  @Override public void openComments(Story story) {
    Intent intent = new Intent(context, CommentsActivity.class);
    intent.putExtra(Constants.STORY_ID, story.getId());
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  @Override public void hideCommentsTextView() {
    tvComments.setVisibility(View.GONE);
  }

  @Override public void hideTitleTextView() {
    tvTitle.setVisibility(View.GONE);
  }

  @OnClick({ R.id.storyListItemTvBy, R.id.storyListItemImgAvatar }) public void onClickByTv() {
    storiesViewHolderPresenter.onClickTvBy();
  }

  @Override public void openUser(String userId) {
    Intent intent = new Intent(context, UserActivity.class);
    intent.putExtra(Constants.USER_ID, userId);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }
}
