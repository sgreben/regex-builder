# Maintainable Regular Expressions
### Matchers, Ready To Eat

A maintainable (and commentable!) way to write regexes in Java code. Everything is expressed as plain Java code; regex fragments can be reused across patterns. No more opaque regex strings!

## Example

```java

import sgreben.mre.CaptureGroup;
import sgreben.mre.Captured;
import sgreben.mre.Mre;

// Build a regular expression with capture groups
CaptureGroup word = Mre.capture(Mre.word());
CaptureGroup sentence = Mre.capture(Mre.sequence(
    Mre.separatedBy(Mre.whitespace(), word),
    Mre.character('.'), 
    Mre.optional(Mre.whitespace())
));
Mre sentences = Mre.many(sentence);

// Compile the expression and process a string.
Matcher sentencesMatcher = Mre.compile(sentences);
sentencesMatcher.read("There are things. Things have properties.")

// Comfortably extract matches and sub-matches
Captured capturedSentences = sentence.getCaptured();
Captured capturedSentence1 = capturedSentences.get(0);
Captured capturedSentence1Words = capturedSentence1.getNested(word);
String capturedSentence1Word2 = capturedSentence1Words.getString(2);
assertEquals("things", capturedSentence1word2);
```