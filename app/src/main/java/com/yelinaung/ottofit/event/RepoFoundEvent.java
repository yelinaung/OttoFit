package com.yelinaung.ottofit.event;

import com.yelinaung.ottofit.model.GitHub;

/**
 * Created by Ye Lin Aung on 14/09/02.
 */
public class RepoFoundEvent {
  public GitHub repo;

  public RepoFoundEvent(GitHub repo) {
    this.repo = repo;
  }
}
