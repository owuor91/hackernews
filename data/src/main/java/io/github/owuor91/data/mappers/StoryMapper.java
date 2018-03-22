package io.github.owuor91.data.mappers;

import io.github.owuor91.data.models.api.StoryApiModel;
import io.github.owuor91.data.models.db.StoryDbModel;
import io.github.owuor91.domain.models.Story;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class StoryMapper {
  public static Story transformFromApi(StoryApiModel storyApiModel) {
    return Story.newBuilder()
        .withBy(storyApiModel.getBy())
        .withDescendants(storyApiModel.getDescendants())
        .withId(storyApiModel.getId())
        .withKids(storyApiModel.getKids())
        .withScore(storyApiModel.getScore())
        .withText(storyApiModel.getText())
        .withTime(storyApiModel.getTime())
        .withTitle(storyApiModel.getTitle())
        .withType(storyApiModel.getType())
        .withUrl(storyApiModel.getUrl())
        .withItemType(storyApiModel.getItemType())
        .build();
  }

  public static StoryDbModel transformToDb(Story story) {
    return StoryDbModel.newBuilder()
        .withBy(story.getBy())
        .withDescendants(story.getDescendants())
        .withId(story.getId())
        .withKids(ArrayStringConverter.safeConvertIntegerArrayToString(story.getKids()))
        .withScore(story.getScore())
        .withText(story.getText())
        .withTime(story.getTime())
        .withTitle(story.getTitle())
        .withType(story.getType())
        .withUrl(story.getUrl())
        .withItemType(story.getItemType())
        .build();
  }

  public static Story transformFromDb(StoryDbModel storyDbModel) {
    return Story.newBuilder()
        .withBy(storyDbModel.getBy())
        .withDescendants(storyDbModel.getDescendants())
        .withId(storyDbModel.getId())
        .withKids(ArrayStringConverter.convertStringToIntegerArray(storyDbModel.getKids()))
        .withScore(storyDbModel.getScore())
        .withText(storyDbModel.getText())
        .withTime(storyDbModel.getTime())
        .withTitle(storyDbModel.getTitle())
        .withType(storyDbModel.getType())
        .withUrl(storyDbModel.getUrl())
        .withItemType(storyDbModel.getItemType())
        .build();
  }
}
