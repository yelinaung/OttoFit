package com.yelinaung.ottogithub;

import com.squareup.okhttp.OkHttpClient;
import com.yelinaung.ottogithub.model.GitHub;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Ye Lin Aung on 14/09/02.
 */
public class Async {

  private static String API_URL = "https://api.github.com";
  private static OkHttpClient okHttpClient = new OkHttpClient();

  public String repo;

  public Async(String repo) {
    this.repo = repo;
  }

  public static RepoService buildApi() {
    return new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.BASIC)
        .setEndpoint(API_URL)
        .setClient(new OkClient(okHttpClient))
        .build()
        .create(RepoService.class);
  }

  public interface RepoService {
    @GET("/search/repositories") void repoList(@Query("q") String q, Callback<GitHub> callback);
  }
}
