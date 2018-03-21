package io.github.owuor91.hackernews.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import io.github.owuor91.data.util.RxUtil;
import io.github.owuor91.hackernews.MyApplication;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class BaseActivity extends AppCompatActivity{
  protected CompositeDisposable compositeDisposable;

  @Override public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    injector().baseInject(this);
  }

  public ActivityComponent injector(){
    return ((MyApplication) getApplicationContext()).getActivityInjector(this);
  }

  public void handleError(Throwable throwable) {
    Toast.makeText(getBaseContext(), throwable.getMessage(),Toast.LENGTH_LONG).show();
  }

  @Override protected void onStart() {
    super.onStart();
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
  }

  protected void dispose() {
    RxUtil.dispose(compositeDisposable);
  }
}
