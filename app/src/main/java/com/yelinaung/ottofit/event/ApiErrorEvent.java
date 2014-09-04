package com.yelinaung.ottofit.event;

import retrofit.RetrofitError;

/**
 * Created by Ye Lin Aung on 14/09/02.
 */

public class ApiErrorEvent {

  public RetrofitError error;

  public ApiErrorEvent(RetrofitError e) {
    this.error = e;
  }
}
