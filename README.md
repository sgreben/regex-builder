# Java Regex Builder

Write regexes as **plain Java code**. Unlike opaque regex strings, commenting your expressions and reusing regex fragments is straightforward.

The **regex-builder** library is implemented as a light-weight wrapper around `java.util.regex`.     

There's a [discussion](https://www.reddit.com/r/java/comments/4tyk90/github_sgrebenregexbuilder_write_regular/) of this project over on the Java reddit.

## API

| Builder method                 | `java.util.regex` syntax |
|--------------------------------|--------------------------|
| Re.repeat(e, N)                | e{N}                     |
| Re.many(e)                     | e*                       |
| Re.many1(e)                    | e+                       |
| Re.optional(e)                 | e?                       |
| Re.capture(e)                  | (e)                      |
| Re.backReference(g)            | \g                       |
| Re.separatedBy(sep, e)         | (?:e((?:sep)(?:e))*)?    |
| Re.separatedBy1(sep, e)        | e(?:(?:sep)(?:e))*       |
| Re.choice(e1,...,eN)           | (?:e1\|...\| eN)         |
| Re.sequence(e1,...,eN)         | e1...eN                  |
| Re.string(s)                   | \\Qs\\E                  |
| Re.word()                      | \w+                      |
| Re.number()                    | \d+                      |
| Re.whitespace()                | \s*                      |
| Re.whitespace1()               | \s+                      |
| CaptureGroup g = Re.capture(e) | (?g e)                   |

## Examples

Imports:
```java
import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.Expression;
import sgreben.regex_builder.Pattern;
import sgreben.regex_builder.Re;
```

### Date (DD/MM/YYYY HH:MM:SS)

- Regex string: `(\d\d\)/(\d\d)\/(\d\d\d\d) (\d\d):(\d\d):(\d\d)`
- Java code:
```java
Expression twoDigits = Re.sequence(Re.digit(), Re.digit());
Expression fourDigits = Re.repeat(Re.digit(), 4);
CaptureGroup day = Re.capture(twoDigits);
CaptureGroup month = Re.capture(twoDigits);
CaptureGroup year = Re.capture(fourDigits);
CaptureGroup hour = Re.capture(twoDigits);
CaptureGroup minute = Re.capture(twoDigits);
CaptureGroup second = Re.capture(twoDigits);
Expression dateExpression = Re.sequence(
  day, '/', month, '/', year, ' ', // DD/MM/YYY
  hour, ':', minute, ':', second,    // HH:MM:SS
);
```

Use the expression like this:
```java
Pattern p = Pattern.compile(dateExpression)
Matcher m = p.matcher("01/05/2015 12:30:22");
m.find();
assertEquals("01", m.group(day));
assertEquals("05", m.group(month));
assertEquals("2015", m.group(year));
assertEquals("12", m.group(hour));
assertEquals("30", m.group(minute));
assertEquals("22", m.group(second));
```

### Hex color

- Regex string: `#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?`
- Java code:
```java
Expression hexDigit = Re.charClass("[a-fA-F0-9]");
Expression threeHexDigits = Re.repeat(hexDigit, 3);
CaptureGroup hexValue = Re.capture(Re.sequence(
    threeHexDigits,              // #FFF  
    Re.optional(threeHexDigits)  // #FFFFFF
));
Expression hexColor = Re.sequence(
  '#', hexValue
);
```

Use the expression like this:
```java
Pattern p = Pattern.compile(hexColor);
Matcher m = p.matcher("#0FAFF3 and #1bf");
m.find();
assertEquals("0FAFF3", m.group(hexValue));
m.find();
assertEquals("1bf", m.group(hexValue));```
```
