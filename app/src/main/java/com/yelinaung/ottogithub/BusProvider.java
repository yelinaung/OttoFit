package com.yelinaung.ottogithub;

import com.squareup.otto.Bus;

/**
 * Created by Ye Lin Aung on 14/08/05.
 */
public class BusProvider {
  private static final Bus BUS = new Bus();

  public BusProvider() {
  }

  public static Bus getInstance() {
    return BUS;
  }
}
