package io.github.owuor91.hackernews.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
  public BaseViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
