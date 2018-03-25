package io.github.owuor91.data.mappers;

import io.github.owuor91.data.models.api.StoryApiModel;
import io.github.owuor91.data.models.db.StoryDbModel;
import io.github.owuor91.domain.models.Story;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by johnowuor on 25/03/2018.
 */

@RunWith(JUnit4.class) public class StoryMapperTest {
  private StoryApiModel storyApiModel;
  private Story story;
  private StoryDbModel storyDbModel;
  private List<Integer> kidsList = new ArrayList<Integer>();

  @Before public void setup() {
    kidsList.add(57380);
    kidsList.add(78473);
    kidsList.add(74628);

    storyApiModel = storyApiModel.newBuilder()
        .withBy("userX")
        .withDescendants(23)
        .withId(47229)
        .withItemType("itemType")
        .withKids(kidsList)
        .withScore(91)
        .withText("storyText")
        .withTime(743862)
        .withTitle("storyTitle")
        .withUrl("https://story.url.de")
        .withType("story")
        .build();

    storyDbModel = storyDbModel.newBuilder()
        .withBy("userX")
        .withDescendants(23)
        .withId(47229)
        .withItemType("itemType")
        .withKids(ArrayStringConverter.safeConvertIntegerArrayToString(kidsList))
        .withScore(91)
        .withText("storyText")
        .withTime(743862)
        .withTitle("storyTitle")
        .withUrl("https://story.url.de")
        .withType("story")
        .build();

    story = story.newBuilder()
        .withBy("userX")
        .withDescendants(23)
        .withId(47229)
        .withItemType("itemType")
        .withKids(kidsList)
        .withScore(91)
        .withText("storyText")
        .withTime(743862)
        .withTitle("storyTitle")
        .withUrl("https://story.url.de")
        .withType("story")
        .build();
  }

  @Test public void shouldConvertApiStoryToStory() {
    Story myStory = StoryMapper.transformFromApi(storyApiModel);

    Assert.assertEquals(myStory.getBy(), storyApiModel.getBy());
    Assert.assertEquals(myStory.getDescendants(), storyApiModel.getDescendants());
    Assert.assertEquals(myStory.getId(), storyApiModel.getId());
    Assert.assertEquals(myStory.getItemType(), storyApiModel.getItemType());
    Assert.assertEquals(myStory.getKids(), storyApiModel.getKids());
    Assert.assertEquals(myStory.getScore(), storyApiModel.getScore());
    Assert.assertEquals(myStory.getText(), storyApiModel.getText());
    Assert.assertEquals(myStory.getTime(), storyApiModel.getTime());
    Assert.assertEquals(myStory.getTitle(), storyApiModel.getTitle());
    Assert.assertEquals(myStory.getUrl(), storyApiModel.getUrl());
    Assert.assertEquals(myStory.getType(), storyApiModel.getType());
  }

  @Test public void shouldConvertStoryToStoryDbModel() {
    StoryDbModel myStoryDbModel = StoryMapper.transformToDb(story);

    Assert.assertEquals(myStoryDbModel.getBy(), story.getBy());
    Assert.assertEquals(myStoryDbModel.getDescendants(), story.getDescendants());
    Assert.assertEquals(myStoryDbModel.getId(), story.getId());
    Assert.assertEquals(myStoryDbModel.getItemType(), story.getItemType());
    Assert.assertEquals(ArrayStringConverter.convertStringToIntegerArray(myStoryDbModel.getKids()), story.getKids());
    Assert.assertEquals(myStoryDbModel.getScore(), story.getScore());
    Assert.assertEquals(myStoryDbModel.getText(), story.getText());
    Assert.assertEquals(myStoryDbModel.getTime(), story.getTime());
    Assert.assertEquals(myStoryDbModel.getTitle(), story.getTitle());
    Assert.assertEquals(myStoryDbModel.getUrl(), story.getUrl());
    Assert.assertEquals(myStoryDbModel.getType(), story.getType());
  }

  @Test public void shouldConvertStoryDbModelToStory() {
    Story myStory = StoryMapper.transformFromDb(storyDbModel);

    Assert.assertEquals(myStory.getBy(), storyDbModel.getBy());
    Assert.assertEquals(myStory.getDescendants(), storyDbModel.getDescendants());
    Assert.assertEquals(myStory.getId(), storyDbModel.getId());
    Assert.assertEquals(myStory.getItemType(), storyDbModel.getItemType());
    Assert.assertEquals(myStory.getKids(), ArrayStringConverter.convertStringToIntegerArray(storyDbModel.getKids()));
    Assert.assertEquals(myStory.getScore(), storyDbModel.getScore());
    Assert.assertEquals(myStory.getText(), storyDbModel.getText());
    Assert.assertEquals(myStory.getTime(), storyDbModel.getTime());
    Assert.assertEquals(myStory.getTitle(), storyDbModel.getTitle());
    Assert.assertEquals(myStory.getUrl(), storyDbModel.getUrl());
    Assert.assertEquals(myStory.getType(), storyDbModel.getType());
  }
}
