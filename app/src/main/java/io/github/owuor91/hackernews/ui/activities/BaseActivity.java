package io.github.owuor91.hackernews.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import io.github.owuor91.hackernews.MyApplication;
import io.github.owuor91.hackernews.di.activity.ActivityComponent;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class BaseActivity extends AppCompatActivity{

  @Override public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
  }

  public ActivityComponent injector(){
    return ((MyApplication) getApplicationContext()).getActivityInjector(this);
  }

  public void handleError(Throwable throwable) {
    Toast.makeText(getBaseContext(), throwable.getMessage(),Toast.LENGTH_LONG).show();
  }
}
