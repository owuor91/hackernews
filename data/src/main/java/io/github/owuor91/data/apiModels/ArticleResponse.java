package io.github.owuor91.data.apiModels;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class ArticleResponse {
  @SerializedName("status") private String status;

  @SerializedName("totalResults") private int totalResults;

  @SerializedName("articles") private List<ArticleApiModel> articleApiModelList;

  public String getStatus() {
    return status;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public List<ArticleApiModel> getArticleApiModelList() {
    return articleApiModelList;
  }
}
