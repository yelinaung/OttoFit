package com.yelinaung.ottofit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.squareup.otto.Subscribe;
import com.squareup.phrase.Phrase;
import com.yelinaung.ottofit.event.ApiErrorEvent;
import com.yelinaung.ottofit.event.RepoFoundEvent;
import com.yelinaung.ottofit.event.SearchRepoEvent;
import com.yelinaung.ottofit.model.GitHub;
import com.yelinaung.ottofit.model.Item;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyActivity extends Activity {

  @InjectView(R.id.search_wrapper) RelativeLayout searchWrapper;
  @InjectView(R.id.reponame_edittext) EditText repoName;
  @InjectView(R.id.search_progress) ProgressBar searchProgress;
  @InjectView(R.id.result) TextView result;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);
    ButterKnife.inject(this);
  }

  @SuppressWarnings("unused") @OnClick(R.id.search_btn) public void searchRepo() {
    if (repoName.getText().toString().trim().length() == 0) {
      repoName.setError("Please enter a repo name");
    } else {
      BusProvider.getInstance().post(new SearchRepoEvent());
      showProgress(true);
      result.setText("");
    }
  }

  @Subscribe public void onRepoSearch(SearchRepoEvent event) {
    Async.buildApi().repoList(repoName.getText().toString().trim(), new Callback<GitHub>() {
      @Override public void success(GitHub gitHub, Response response) {
        if (gitHub != null) {
          BusProvider.getInstance().post(new RepoFoundEvent(gitHub));
        }
      }

      @Override public void failure(RetrofitError error) {
        BusProvider.getInstance().post(new ApiErrorEvent(error));
      }
    });
  }

  @Subscribe public void onRepoFound(RepoFoundEvent event) {
    showProgress(false);
    GitHub gh = event.repo;
    Toast.makeText(this, "Total repo found : " + gh.items.size(), Toast.LENGTH_SHORT).show();

    for (Item i : gh.items) {
      if (i.language != null) {
        CharSequence formatted = Phrase.from(this, R.string.repo)
            .put("name", i.name)
            .put("language", i.language)
            .format();
        result.append(formatted);
      }
    }
  }

  @Subscribe public void onSearchError(ApiErrorEvent event) {
    showProgress(false);
    Toast.makeText(this, event.error.getMessage(), Toast.LENGTH_SHORT).show();
    Log.i("error", event.error.getMessage());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    return id == R.id.action_settings || super.onOptionsItemSelected(item);
  }

  public void showProgress(final boolean show) {
    // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
    // for very easy animations. If available, use these APIs to fade-in
    // the progress spinner.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

      searchWrapper.setVisibility(show ? View.GONE : View.VISIBLE);
      searchWrapper.animate()
          .setDuration(shortAnimTime)
          .alpha(show ? 0 : 1)
          .setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              searchWrapper.setVisibility(show ? View.GONE : View.VISIBLE);
            }
          });

      searchProgress.setVisibility(show ? View.VISIBLE : View.GONE);
      searchProgress.animate()
          .setDuration(shortAnimTime)
          .alpha(show ? 1 : 0)
          .setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              searchProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            }
          });
    } else {
      // The ViewPropertyAnimator APIs are not available, so simply show
      // and hide the relevant UI components.
      searchProgress.setVisibility(show ? View.VISIBLE : View.GONE);
      searchWrapper.setVisibility(show ? View.GONE : View.VISIBLE);
    }
  }

  @Override protected void onResume() {
    super.onResume();
    BusProvider.getInstance().register(this);
  }

  @Override protected void onPause() {
    super.onPause();
    BusProvider.getInstance().unregister(this);
  }
}

