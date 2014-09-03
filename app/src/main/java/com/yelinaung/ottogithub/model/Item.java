package com.yelinaung.ottogithub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ye Lin Aung on 14/09/02.
 */
public class Item {
  @Expose public Integer id;
  @Expose public String name;
  @SerializedName("full_name") @Expose public String fullName;
  @Expose public Owner owner;
  @SerializedName("public") @Expose public Boolean _public;
  @SerializedName("html_url") @Expose public String htmlUrl;
  @Expose public String description;
  @Expose public Boolean fork;
  @Expose public String url;
  @SerializedName("created_at") @Expose public String createdAt;
  @SerializedName("updated_at") @Expose public String updatedAt;
  @SerializedName("pushed_at") @Expose public String pushedAt;
  @Expose public String homepage;
  @Expose public Integer size;
  @SerializedName("stargazers_count") @Expose public Integer stargazersCount;
  @SerializedName("watchers_count") @Expose public Integer watchersCount;
  @Expose public String language;
  @SerializedName("forks_count") @Expose public Integer forksCount;
  @SerializedName("open_issues_count") @Expose public Integer openIssuesCount;
  @SerializedName("master_branch") @Expose public String masterBranch;
  @SerializedName("default_branch") @Expose public String defaultBranch;
  @Expose public Double score;
}
