# Regex Builder for Java

A maintainable way of writing regexes in Java. No more opaque regex strings -- everything is expressed as **plain Java code**.

The **regex-builder** library is implemented as a light-weight wrapper around `java.util.regex`.

## Example

```java

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.expression.Expression;
import sgreben.regex_builder.RegexBuilder;

// Build a regular expression with capture groups
CaptureGroup word = RegexBuilder.capture(
    RegexBuilder.word()
);
CaptureGroup sentence = RegexBuilder.capture(
          RegexBuilder.sequence(
              RegexBuilder.separatedBy(RegexBuilder.whitespace(), word),
              RegexBuilder.character('.')
          )
      );
// Compile the expression and process a string.
Pattern sentencePattern = RegexBuilder.compile(
    RegexBuilder.sequence(sentence, RegexBuilder.optional(RegexBuilder.whitespace()))
);
Matcher sentenceMatcher = sentencePattern.matcher("There are things. Things have properties.");
sentenceMatcher.find();

// Comfortably extract matches and sub-matches
String firstSentence = sentenceMatcher.group(sentence);
sentenceMatcher.find();
String secondSentence = sentenceMatcher.group(sentence);
```