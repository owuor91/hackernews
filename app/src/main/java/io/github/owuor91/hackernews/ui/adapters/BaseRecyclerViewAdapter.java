package io.github.owuor91.hackernews.ui.adapters;

import android.support.v7.widget.RecyclerView;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;
import io.github.owuor91.hackernews.di.adapter.AdapterComponent;

/**
 * Created by johnowuor on 22/03/2018.
 */

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
  private final ActivityComponent activityComponent;

  public BaseRecyclerViewAdapter(ActivityComponent activityComponent) {
    this.activityComponent = activityComponent;
  }

  protected AdapterComponent injector() {
    return activityComponent.adapterBuilder().build();
  }
}
