package io.github.owuor91.data.mappers;

import io.github.owuor91.domain.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnowuor on 22/03/2018.
 */

public class ArrayStringConverter {
  public static ArrayList<Integer> convertStringToIntegerArray(String stringOfIntegers) {
    ArrayList<Integer> integerArrayList = new ArrayList<Integer>();

    if (stringOfIntegers != null && !stringOfIntegers.equals("")) {
      String[] splitOriginalString = stringOfIntegers.split(",");
      for (String str : splitOriginalString) {
        integerArrayList.add(Integer.parseInt(str));
      }
    }
    return integerArrayList;
  }

  public static String convertIntegerArrayToString(List<Integer> values) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < values.size(); i++) {
      Integer integer = values.get(i);
      builder.append(integer);

      if ((i + 1) < values.size()) {
        builder.append(Constants.COMMA);
      }
    }
    return builder.toString();
  }

  public static String safeConvertIntegerArrayToString(List<Integer> values) {
    if (values != null && values.size() > 0) {
      return convertIntegerArrayToString(values);
    } else {
      return Constants.EMPTY_STRING;
    }
  }
}
