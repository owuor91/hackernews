package io.github.owuor91.hackernews.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
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
  @BindView(R.id.storyListItemTvReadMore) TextView tvReadMore;

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
    CharSequence sequence = Html.fromHtml(text);
    SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
    URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
    for (URLSpan span : urls) {
      makeLinkClickable(strBuilder, span);
    }
    tvText.setText(strBuilder);
    tvText.setMovementMethod(LinkMovementMethod.getInstance());
  }

  protected void makeLinkClickable(SpannableStringBuilder ssBuilder, final URLSpan urlSpan) {
    int start = ssBuilder.getSpanStart(urlSpan);
    int end = ssBuilder.getSpanEnd(urlSpan);
    int flags = ssBuilder.getSpanFlags(urlSpan);
    ClickableSpan clickable = new ClickableSpan() {
      public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSpan.getURL()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
      }
    };
    ssBuilder.setSpan(clickable, start, end, flags);
    ssBuilder.removeSpan(urlSpan);
  }

  @Override public void hideStoryTextView() {
    tvText.setVisibility(View.GONE);
  }

  @Override public void setScore(int score) {
    tvScore.setText(String.valueOf(score));
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

  @Override public void hideReadMoreLink() {
    tvReadMore.setVisibility(View.GONE);
  }
}
