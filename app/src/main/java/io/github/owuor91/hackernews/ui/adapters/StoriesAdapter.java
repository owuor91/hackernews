package io.github.owuor91.hackernews.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;
import io.github.owuor91.presentation.home.StoriesAdapterPresenter;
import javax.inject.Inject;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoriesAdapter extends BaseRecyclerViewAdapter<StoriesViewHolder> implements StoriesAdapterPresenter.View {

  @Inject public StoriesAdapterPresenter storiesAdapterPresenter;
  @Inject LayoutInflater layoutInflater;
  private Context context;

  public StoriesAdapter(ActivityComponent activityComponent, Context context) {
    super(activityComponent);
    injector().inject(this);
    storiesAdapterPresenter.setView(this);
    this.context = context;
  }

  @Override public StoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(StoriesViewHolder.LAYOUT_ID, parent, false);
    return new StoriesViewHolder(view, injector());
  }

  @Override public void onBindViewHolder(StoriesViewHolder holder, int position) {
    holder.storiesViewHolderPresenter.setPosition(position);
    holder.storiesViewHolderPresenter.setStoriesAdapterPresenter(storiesAdapterPresenter);
    holder.storiesViewHolderPresenter.bind();
  }

  @Override public int getItemCount() {
    return storiesAdapterPresenter.getCount();
  }

  @Override public void handleError(Throwable throwable) {
  }

  @Override public void notifyAdapter() {
    notifyDataSetChanged();
  }

  @Override public void openLink(String link) {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }
}
