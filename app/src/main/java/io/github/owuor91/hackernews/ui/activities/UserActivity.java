package io.github.owuor91.hackernews.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import io.github.owuor91.domain.Constants;
import io.github.owuor91.domain.models.User;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.hackernews.ui.viewutils.TextLinkUtil;
import io.github.owuor91.presentation.user.UserPresenter;
import javax.inject.Inject;
import org.joda.time.DateTime;

public class UserActivity extends BaseActivity implements UserPresenter.View {
  @BindView(R.id.userActivityToolbar) Toolbar toolbar;
  @BindView(R.id.userActivityTvToolbar) TextView tvToolbar;
  @BindView(R.id.userActivityTvAbout) TextView tvAbout;
  @BindView(R.id.userActivityTvCreated) TextView tvCreated;
  @BindView(R.id.userActivityTvKarma) TextView tvKarma;
  @BindView(R.id.userActivityTvSubmitted) TextView tvSubmitted;
  @BindView(R.id.userActivityProgressBar) ProgressBar progressBar;
  @Inject UserPresenter userPresenter;
  private String userId;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);
    injector().inject(this);

    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      userId = bundle.getString(Constants.USER_ID);
    }
  }

  @Override protected void onStart() {
    super.onStart();
    userPresenter.setView(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    userPresenter.getUserDetails(userId);
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void displayUser(User user) {
    tvToolbar.setText(user.getUserId());
    if (!TextUtils.isEmpty(user.getAbout())) {
      TextLinkUtil.setText(user.getAbout(), tvAbout, getBaseContext());
    }

    DateTime joinedDate = new DateTime(user.getCreated() * 1000);
    String month = joinedDate.toString("MMMM");
    String year = joinedDate.toString("yyyy");

    tvCreated.setText(String.format("%s %s", month, year));
    tvKarma.setText(String.valueOf(user.getKarma()));
    tvSubmitted.setText(String.format("%s %s %s", getString(R.string.submitted), user.getSubmitted().size(),
        getString(R.string.items)));
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
