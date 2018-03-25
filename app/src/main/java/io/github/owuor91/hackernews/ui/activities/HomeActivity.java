package io.github.owuor91.hackernews.ui.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.hackernews.ui.BottomNavUtil;
import io.github.owuor91.hackernews.ui.fragments.AskStoriesFragment;
import io.github.owuor91.hackernews.ui.fragments.JobStoriesFragment;
import io.github.owuor91.hackernews.ui.fragments.ShowStoriesFragment;
import io.github.owuor91.hackernews.ui.fragments.TopStoriesFragment;
import io.github.owuor91.presentation.home.HomePresenter;
import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomePresenter.View {
  @Inject HomePresenter homePresenter;

  @BindView(R.id.homeActivityToolbar) Toolbar toolbar;
  @BindView(R.id.homeActivityFramelayout) FrameLayout frameLayout;
  @BindView(R.id.homeActivityBottomNavView) BottomNavigationView bottomNavigationView;
  @BindView(R.id.homeActivityTvToolbar) TextView tvToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    setSupportActionBar(toolbar);
    injector().inject(this);
  }

  @Override protected void onStart() {
    super.onStart();
    homePresenter.setView(this);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    setToolbarTitleText(getString(R.string.topStories));

    getSupportFragmentManager().beginTransaction()
        .replace(R.id.homeActivityFramelayout, new TopStoriesFragment())
        .addToBackStack(null)
        .commit();

    BottomNavUtil.removeShiftMode(bottomNavigationView);
  }

  @Override protected void onResume() {
    super.onResume();

    bottomNavigationView.setSelectedItemId(R.id.showStories);
    openSelectedTab(new ShowStoriesFragment(), getString(R.string.showStories));

    bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
      switch (menuItem.getItemId()) {
        case R.id.topStories:
          openSelectedTab(new TopStoriesFragment(), getString(R.string.topStories));
          break;
        case R.id.showStories:
          openSelectedTab(new ShowStoriesFragment(), getString(R.string.showStories));
          break;
        case R.id.jobStories:
          openSelectedTab(new JobStoriesFragment(), getString(R.string.jobStories));
          break;
        case R.id.askStories:
          openSelectedTab(new AskStoriesFragment(), getString(R.string.askStories));
      }
      return true;
    });
  }

  private void openSelectedTab(Fragment fragment, String toolbarTitleText) {
    setToolbarTitleText(toolbarTitleText);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.homeActivityFramelayout, fragment)
        .addToBackStack(null)
        .commit();
  }

  private void setToolbarTitleText(String toolbarTitleText) {
    tvToolbar.setText(toolbarTitleText);
  }

  @Override protected void dispose() {
    super.dispose();
    homePresenter.dispose();
  }
}
