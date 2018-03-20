package io.github.owuor91.data.mappers;

import io.github.owuor91.data.apiModels.SourceApiModel;
import io.github.owuor91.domain.models.Source;

/**
 * Created by johnowuor on 20/03/2018.
 */

public class SourceMapper {
  public static Source transformFromApi(SourceApiModel sourceApiModel){
    return Source.newBuilder()
        .withId(sourceApiModel.getId())
        .withName(sourceApiModel.getName())
        .build();
  }
}
