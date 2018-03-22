package io.github.owuor91.hackernews.ui.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.hackernews.di.adapter.AdapterComponent;
import io.github.owuor91.presentation.home.StoriesViewHolderPresenter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoriesViewHolder extends BaseViewHolder implements StoriesViewHolderPresenter.View {
  public static final int LAYOUT_ID = R.layout.story_list_item;

  @Inject public StoriesViewHolderPresenter storiesViewHolderPresenter;
  @Inject @Named(DIConstants.APP) Context context;

  @BindView(R.id.storyListItemTvBy) TextView tvBy;
  @BindView(R.id.storyListItemTvTitle) TextView tvTitle;
  @BindView(R.id.storyListItemTvText) TextView tvText;
  @BindView(R.id.storyListItemTvUrl) TextView tvUrl;
  @BindView(R.id.storyListItemTvScore) TextView tvScore;

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
    tvText.setText(text);
  }

  @Override public void setScore(int score) {
    tvScore.setText(String.valueOf(score));
  }

  @Override public void setUrl(String url) {
    tvUrl.setText(url);
  }
}
