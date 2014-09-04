package com.yelinaung.ottofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ye Lin Aung on 14/09/02.
 */
public class Owner {
  @Expose private String login;
  @Expose private Integer id;
  @SerializedName("avatar_url") @Expose private String avatarUrl;
  @SerializedName("gravatar_id") @Expose private String gravatarId;
  @Expose private String url;
  @SerializedName("received_events_url") @Expose private String receivedEventsUrl;
  @Expose private String type;
}
