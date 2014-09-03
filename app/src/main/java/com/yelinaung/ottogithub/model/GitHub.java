package com.yelinaung.ottogithub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ye Lin Aung on 14/09/02.
 */
public class GitHub {
  @SerializedName("total_count") @Expose public Integer totalCount;
  @SerializedName("incomplete_results") @Expose public Boolean incompleteResults;
  @Expose public List<Item> items = new ArrayList<Item>();
}
