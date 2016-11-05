# Java Regex Builder

Write regexes as **plain Java code**. Unlike opaque regex strings, commenting your expressions and reusing regex fragments is straightforward.

The **regex-builder** library is implemented as a light-weight wrapper around `java.util.regex`.     

There's a [discussion](https://www.reddit.com/r/java/comments/4tyk90/github_sgrebenregexbuilder_write_regular/) of this project over on the Java reddit.

## Maven dependency

```xml
<dependency>
  <groupId>com.github.sgreben</groupId>
  <artifactId>regex-builder</artifactId>
  <version>1.0.1-SNAPSHOT</version>
</dependency>
```

## Examples

Imports:
```java
import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.Expression;
import sgreben.regex_builder.Pattern;
import static sgreben.regex_builder.CharClass.*;
import static sgreben.regex_builder.Re.*;
```

### Apache log

- Regex string: `^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+)$`
- Java code:
```java

CaptureGroup ip, client, user, dateTime, method, request, protocol, responseCode, size;
Expression nonWhitespace = repeat1(nonWhitespaceChar());

ip = capture(nonWhitespace);
client = capture(nonWhitespace);
user = capture(nonWhitespace);
dateTime = capture(sequence(
        repeat1(union(wordChar(),':','/')),  // 21/Jul/2014:9:55:27
        whitespaceChar(),
        oneOf("+\\-"),     // -
        repeat(digit(), 4) // 0800
));
method = capture(nonWhitespace);
request = capture(nonWhitespace);
protocol = capture(nonWhitespace);
responseCode = capture(repeat(digit(), 3));
size = capture(repeat1(digit()));

Pattern p = Pattern.compile(sequence(
        beginInput(),
        ip, ' ', client, ' ', user, " [", dateTime, "] \"", method, ' ', request, ' ', protocol, "\" ", responseCode, ' ', size,
        endInput()
));
```

### Date (DD/MM/YYYY HH:MM:SS)

- Regex string: `(\d\d\)/(\d\d)\/(\d\d\d\d) (\d\d):(\d\d):(\d\d)`
- Java code:
```java
Expression twoDigits = repeat(digit(), 2);
Expression fourDigits = repeat(digit(), 4);
CaptureGroup day = capture(twoDigits);
CaptureGroup month = capture(twoDigits);
CaptureGroup year = capture(fourDigits);
CaptureGroup hour = capture(twoDigits);
CaptureGroup minute = capture(twoDigits);
CaptureGroup second = capture(twoDigits);
Expression dateExpression = sequence(
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
Expression threeHexDigits = repeat(hexDigit(), 3);
CaptureGroup hexValue = capture(
    threeHexDigits,              // #FFF  
    optional(threeHexDigits)  // #FFFFFF
);
Expression hexColor = sequence(
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
assertEquals("1bf", m.group(hexValue));
```

## API

### Expression builder

| Builder method              | `java.util.regex` syntax |
|-----------------------------|--------------------------|
| repeat(e, N)                | e{N}                     |
| repeat(e)                   | e*                       |
| repeat(e).possessive()      | e*+                      |
| repeatPossessive(e)         | e*+                      |
| repeat1(e)                  | e+                       |
| repeat1(e).possessive()     | e++                      |
| repeat1Possessive(e)        | e++                      |
| optional(e)                 | e?                       |
| optional(e).possessive()    | e?+                      |
| optionalPossessive(e)       | e?+                      |
| capture(e)                  | (e)                      |
| positiveLookahead(e)        | (?=e)                    |
| negativeLookahead(e)        | (?!e)                    |
| positiveLookbehind(e)       | (?<=e)                   |
| negativeLookbehind(e)       | (?<!e)                   |
| backReference(g)            | \g                       |
| separatedBy(sep, e)         | (?:e((?:sep)(?:e))*)?    |
| separatedBy1(sep, e)        | e(?:(?:sep)(?:e))*       |
| choice(e1,...,eN)           | (?:e1\|...\| eN)         |
| sequence(e1,...,eN)         | e1...eN                  |
| string(s)                   | \Qs\E                    |
| word()                      | \w+                      |
| number()                    | \d+                      |
| whitespace()                | \s*                      |
| whitespace1()               | \s+                      |
| CaptureGroup g = capture(e) | (?g e)                   |

### CharClass builder

| Builder method                        | `java.util.regex` syntax |
|---------------------------------------|--------------------------|
| range(from, to)             | [from-to]                |
| range(f1, t1, ..., fN, tN)  | [f1-t1f2-t2...fN-tN]     |
| oneOf("abcde")              | [abcde]                  |
| union(class1, ..., classN)  | [[class1]...[classN]]    |
| complement(class1)          | [\^[class1]]              |
| anyChar()                   | .                        |
| digit()                     | \d                       |
| nonDigit()                  | \D                       |
| hexDigit()                  | [a-fA-F0-9]              |
| nonHexDigit()               | [\^[a-fA-F0-9]]           |
| wordChar()                  | \w                       |
| nonWordChar()               | \W                       |
| wordBoundary()              | \b                       |
| nonWordBoundary()           | \B                       |
| whitespaceChar()            | \s                       |
| nonWhitespaceChar()         | \S                       |
| verticalWhitespaceChar()    | \v                       |
| nonVerticalWhitespaceChar() | \V                       |
| horizontalWhitespaceChar()  | \h                       |
| nonHorizontalWhitespaceChar()| \H                      |