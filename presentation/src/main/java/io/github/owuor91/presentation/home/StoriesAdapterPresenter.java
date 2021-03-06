package io.github.owuor91.presentation.home;

import io.github.owuor91.domain.models.Story;
import io.github.owuor91.presentation.BasePresenter;
import io.github.owuor91.presentation.comments.CommentsPresenter;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoriesAdapterPresenter implements BasePresenter {
  private List<Story> stories;
  private View view;
  private JobStoriesPresenter jobStoriesPresenter;
  private TopStoriesPresenter topStoriesPresenter;
  private AskStoriesPresenter askStoriesPresenter;
  private ShowStoriesPresenter showStoriesPresenter;
  private CommentsPresenter commentsPresenter;

  @Inject public StoriesAdapterPresenter() {
  }

  public void setJobStoriesPresenter(JobStoriesPresenter jobStoriesPresenter) {
    this.jobStoriesPresenter = jobStoriesPresenter;
  }

  public void setTopStoriesPresenter(TopStoriesPresenter topStoriesPresenter) {
    this.topStoriesPresenter = topStoriesPresenter;
  }

  public void setAskStoriesPresenter(AskStoriesPresenter askStoriesPresenter) {
    this.askStoriesPresenter = askStoriesPresenter;
  }

  public void setShowStoriesPresenter(ShowStoriesPresenter showStoriesPresenter) {
    this.showStoriesPresenter = showStoriesPresenter;
  }

  public void setCommentsPresenter(CommentsPresenter commentsPresenter) {
    this.commentsPresenter = commentsPresenter;
  }

  public void setView(View view) {
    this.view = view;
  }

  public int getCount() {
    return stories.size();
  }

  public Story getStoryAt(int position) {
    return stories.get(position);
  }

  public void onDataChange(List<Story> stories) {
    this.stories = stories;
    view.notifyAdapter();
  }

  @Override public void dispose() {
  }

  public void onStoryClick(int position) {
    view.openLink(stories.get(position).getUrl());
  }

  public interface View extends BasePresenter.View {
    void notifyAdapter();

    void openLink(String link);
  }
}
