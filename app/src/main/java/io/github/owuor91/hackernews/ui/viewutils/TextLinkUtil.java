package io.github.owuor91.hackernews.ui.viewutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import io.github.owuor91.domain.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by johnowuor on 25/03/2018.
 */

public class TextLinkUtil {
  public static void setText(String text, TextView textView, Context context) {
    CharSequence sequence = Html.fromHtml(text);
    SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
    URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
    for (URLSpan span : urls) {
      makeLinkClickable(strBuilder, span, context);
    }
    textView.setText(strBuilder);
    textView.setMovementMethod(LinkMovementMethod.getInstance());
  }

  public static void makeLinkClickable(SpannableStringBuilder ssBuilder, final URLSpan urlSpan, Context context) {
    int start = ssBuilder.getSpanStart(urlSpan);
    int end = ssBuilder.getSpanEnd(urlSpan);
    int flags = ssBuilder.getSpanFlags(urlSpan);
    ClickableSpan clickable = new ClickableSpan() {
      public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSpan.getURL()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
      }
    };
    ssBuilder.setSpan(clickable, start, end, flags);
    ssBuilder.removeSpan(urlSpan);
  }

  public static List<String> getUrls(String text) {
    List<String> stringList = Arrays.asList(text.split(" "));
    List<String> urls = new ArrayList<String>();
    for (String s : stringList) {
      if (s.startsWith("http")) {
        urls.add(s);
      }
    }
    return urls;
  }

  public static String makeUrls(String text) {
    List<String> urls = getUrls(text);
    List<String> textArray = Arrays.asList(text.split(" "));
    String finalText = Constants.EMPTY_STRING;
    for (String s : textArray) {
      if (urls.contains(s)) {
        s = "<a href=\"" + s + "\">" + s + "</a>";
      }
      finalText += s;
      finalText += Constants.SPACE;
    }
    return finalText;
  }
}
