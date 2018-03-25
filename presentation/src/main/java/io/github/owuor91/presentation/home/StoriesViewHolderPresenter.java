package io.github.owuor91.presentation.home;

import android.content.Context;
import android.text.TextUtils;
import io.github.owuor91.domain.di.DIConstants;
import io.github.owuor91.domain.models.Story;
import io.github.owuor91.presentation.BasePresenter;
import io.github.owuor91.presentation.R;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoriesViewHolderPresenter implements BasePresenter {
  private Context context;
  private View view;
  private int position;
  private StoriesAdapterPresenter storiesAdapterPresenter;

  @Inject public StoriesViewHolderPresenter(@Named(DIConstants.APP) Context context) {
    this.context = context;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void setStoriesAdapterPresenter(StoriesAdapterPresenter storiesAdapterPresenter) {
    this.storiesAdapterPresenter = storiesAdapterPresenter;
  }

  public void bind() {
    Story story = storiesAdapterPresenter.getStoryAt(position);

    view.setBy(story.getBy());

    view.setTitle(story.getTitle());

    if (!TextUtils.isEmpty(story.getText())) {
      if (story.getText().length() > 160) {
        view.setText(String.format("%s%s", story.getText().substring(0, 159), context.getString(R.string.ellipsis)));
        view.showReadMoreLink();
      } else {
        view.setText(story.getText());
        view.hideReadMoreLink();
      }
    } else {
      view.hideStoryTextView();
      view.hideReadMoreLink();
    }

    if (!TextUtils.isEmpty(story.getUrl())) {
      view.setUrl(story.getUrl());
    } else {
      view.hideUrlView();
    }

    view.setScore(story.getScore());
  }

  @Override public void dispose() {

  }

  public void onClickLink() {
    storiesAdapterPresenter.onStoryClick(position);
  }

  public void onClickReadMore() {
    view.setText(storiesAdapterPresenter.getStoryAt(position).getText());
    view.hideReadMoreLink();
  }

  public interface View extends BasePresenter.View {
    void setBy(String by);

    void setTitle(String title);

    void setText(String text);

    void hideStoryTextView();

    void setScore(int score);

    void setUrl(String url);

    void hideUrlView();

    void showReadMoreLink();

    void hideReadMoreLink();
  }
}
