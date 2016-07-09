# Maintainable Regular Expressions

A maintainable (and commentable!) way to write regexes in Java. No more opaque regex strings -- everything is expressed as **plain Java code**.


## Example

```java

import sgreben.mre.CaptureGroup;
import sgreben.mre.Captured;
import sgreben.mre.expression.Expression;
import sgreben.mre.Mre;

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
Expression sentences = Mre.many(sentence);

// Compile the expression and process a string.
Pattern sentencesPattern = Mre.compile(sentences);
Matcher sentencesMatcher = sentencesPattern.matcher("There are things. Things have properties.");

// Comfortably extract matches and sub-matches
Captured firstSentence = sentence.getCaptured().get(0);
Captured thirdWord = firstSentence.getNested(word).get(2);
assertEquals("things", thirdWord.getString());
```