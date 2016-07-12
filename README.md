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
  Re.sequence(                             // A sentence is a sequence of
    Re.separatedBy(Re.whitespace(), word), // words separated by whitespace
    Re.character('.')                      // and followed by a period.
  )
);
      
// Compile the expression and process a string.
Pattern sentencePattern = Re.compile(
  Re.sequence(sentence, Re.optional(Re.whitespace())) // Optional whitespace at the end
);
Matcher sentenceMatcher = sentencePattern.matcher("There are things. Things have properties.");

// Comfortably extract matches and sub-matches
sentenceMatcher.find();
String firstSentence = sentenceMatcher.group(sentence);
assertEqual("There are things", firstSentence);
sentenceMatcher.find();
String secondSentence = sentenceMatcher.group(sentence);
assertEqual("Things have properties", secondSentence);
```

## More examples

### Hex color

- Regex string: `^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$`
- Java code:
```java
Expression hexDigit = Re.charClass('[a-fA-F0-9]');
Expression threeHexDigits = Re.repeat(hexDigit, 3);
Expression hexValue = Re.sequence(
  threeHexDigits, Re.optional(threeHexDigits),
);
Expression hexColor = Re.sequence(
  Re.beginLine(), 
  Re.character('#'), hexValue, // #FFF or #FFFFFF 
  Re.endLine()
);

```

### Date (DD/MM/YYYY HH:MM:SS)

- Regex string: `^(\d\d\)/(\d\d)\/(\d\d\d\d) (\d\d):(\d\d):(\d\d)$`
- Java code:
```java
Expression twoDigits = Re.sequence(Re.digit(), Re.digit());
Expression fourDigits = Re.repeat(Re.digit(), 4);
Expression slash = Re.character('/');
Expression colon = Re.character(':');
Expression space = Re.character(' ');
CaptureGroup day = Re.capture(twoDigits);
CaptureGroup month = Re.capture(twoDigits);
CaptureGroup year = Re.capture(fourDigits);
CaptureGroup hour = Re.capture(twoDigits);
CaptureGroup minute = Re.capture(twoDigits);
CaptureGroup second = Re.capture(twoDigits);
Expression dateExpression = Re.sequence(
  Re.beginLine(),
  day, slash, month, slash, year, space, // DD/MM/YYY
  hour, colon, minute, colon, second,    // HH:MM:SS
  Re.endLine(),
);