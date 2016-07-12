# Regex Builder for Java

A maintainable way of writing regexes in Java. No more opaque regex strings -- everything is expressed as **plain Java code**.

The **regex-builder** library is implemented as a light-weight wrapper around `java.util.regex`.

## Example

```java

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.expression.Expression;
import sgreben.regex_builder.RegexBuilder;

// Build a regular expression with capture groups
CaptureGroup word = Mre.capture(
    Mre.word()
);
CaptureGroup sentence = Mre.capture(
    Mre.sequence(                              // A sentence is a sequence of
      Mre.separatedBy(Mre.whitespace(), word), // words separated by whitespace
      Mre.character('.'),                      // and followed by a period
      Mre.optional(Mre.whitespace())           // (and perhaps more whitespace).
    )
);
// Compile the expression and process a string.
Pattern sentencePattern = Mre.compile(sentence);
Matcher sentenceMatcher = sentencePattern.matcher("There are things. Things have properties.");
sentenceMatcher.find();

// Comfortably extract matches and sub-matches
String firstSentence = sentenceMatcher.group(sentence);
sentenceMatcher.find();
String secondSentence = sentenceMatcher.group(sentence);
```