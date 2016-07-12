# Regex Builder for Java

Write regexes as **plain Java code**. Unlike opaque regex strings, commenting your expressions and reusing regex fragments is straightforward.

The **regex-builder** library is implemented as a light-weight wrapper around `java.util.regex`.

## Example

```java

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.expression.Expression;
import sgreben.regex_builder.Re;

// Build a regular expression with capture groups
CaptureGroup word = Re.capture(
    Re.word()
);
CaptureGroup sentence = Re.capture(
          Re.sequence(
              Re.separatedBy(Re.whitespace(), word),
              Re.character('.')
          )
      );
      
// Compile the expression and process a string.
Pattern sentencePattern = Re.compile(
    Re.sequence(sentence, Re.optional(Re.whitespace()))
);
Matcher sentenceMatcher = sentencePattern.matcher("There are things. Things have properties.");
sentenceMatcher.find();

// Comfortably extract matches and sub-matches
String firstSentence = sentenceMatcher.group(sentence);
sentenceMatcher.find();
String secondSentence = sentenceMatcher.group(sentence);
```