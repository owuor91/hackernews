package io.github.owuor91.hackernews.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import io.github.owuor91.domain.models.Article;
import io.github.owuor91.hackernews.MyApplication;
import io.github.owuor91.hackernews.R;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;
import io.github.owuor91.presentation.home.HomePresenter;
import java.util.List;
import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HomePresenter.View{
  @Inject HomePresenter homePresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    injector().inject(this);
    homePresenter.setView(this);

  }

  @Override protected void onStart() {
    super.onStart();
    homePresenter.getArticles();
  }

  @Override public void showArticles(List<Article> articleList) {
    Toast.makeText(getBaseContext(), "We have retrieved "+articleList.size()+" articles", Toast.LENGTH_LONG).show();
  }


}
