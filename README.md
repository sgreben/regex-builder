# Java Regex Builder

Write regexes as **plain Java code**. Unlike opaque regex strings, commenting your expressions and reusing regex fragments is straightforward.

The **regex-builder** library is implemented as a light-weight wrapper around `java.util.regex`. It consists of three main components: the expression builder `Re`, its fluent API equivalent `FluentRe`, and the character class builder  `CharClass`. The components are introduced in the examples below as well as in the API overview tables at the end of this document.

There's a [discussion](https://www.reddit.com/r/java/comments/4tyk90/github_sgrebenregexbuilder_write_regular/) of this project over on the Java subreddit.

- [Maven dependency](#maven-dependency)
- [Examples](#examples)
  - [Apache log](#apache-log)
  - [Apache log (fluent API)](#apache-log-fluent-api)
  - [Date (DD/MM/YYYY HH:MM:SS)](#date-ddmmyyyy-hhmmss)
  - [Hex color](#hex-color)
- [Reusing expressions](#reusing-expressions)
  - [Reusable Apache log expression](#reusable-apache-log-expression)
- [API](#api)
  - [Expression builder](#expression-builder)
  - [CharClass builder](#charclass-builder)

## Maven dependency

```xml
<dependency>
  <groupId>com.github.sgreben</groupId>
  <artifactId>regex-builder</artifactId>
  <version>1.1.0</version>
</dependency>
```

## Examples

Imports:
```java
import com.github.sgreben.regex_builder.CaptureGroup;
import com.github.sgreben.regex_builder.Expression;
import com.github.sgreben.regex_builder.Pattern;
import static com.github.sgreben.regex_builder.CharClass.*;
import static com.github.sgreben.regex_builder.Re.*;
```

### Apache log

- Regex string: `(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+)`
- Java code:
```java

CaptureGroup ip, client, user, dateTime, method, request, protocol, responseCode, size;
Expression token = repeat1(nonWhitespaceChar());

ip = capture(token);
client = capture(token);
user = capture(token);
dateTime = capture(sequence(
  repeat1(union(wordChar(),':','/')),  whitespaceChar(), oneOf("+\\-"), repeat(digit(), 4)
));
method = capture(token);
request = capture(token);
protocol = capture(token);
responseCode = capture(repeat(digit(), 3));
size = capture(number());

Pattern p = Pattern.compile(sequence(
  ip, ' ', client, ' ', user, " [", dateTime, "] \"", method, ' ', request, ' ', protocol, "\" ", responseCode, ' ', size
));
```
Note that capture groups are plain java objects - no need to mess around with group indices or string group names. You can use the expression like this:
```java
String logLine = "127.0.0.1 - - [21/Jul/2014:9:55:27 -0800] \"GET /home.html HTTP/1.1\" 200 2048";
Matcher m = p.matcher(logLine);

assertTrue(m.matches());

assertEquals("127.0.0.1", m.group(ip));
assertEquals("-", m.group(client));
assertEquals("-", m.group(user));
assertEquals("21/Jul/2014:9:55:27 -0800", m.group(dateTime));
assertEquals("GET", m.group(method));
assertEquals("/home.html", m.group(request));
assertEquals("HTTP/1.1", m.group(protocol));
assertEquals("200", m.group(responseCode));
assertEquals("2048", m.group(size));
```

Or, if you'd like to rewrite the log to a simpler "ip - request - response code" format, you can simply do
```java
String result = m.replaceFirst(replacement(ip, " - ", request, " - ", responseCode));
```

### Apache log (fluent API)

The above example can also be expressed using the fluent API implemented in `FluentRe`. To use it, you have import it as

```java
import static com.github.sgreben.regex_builder.CharClass.*;
import com.github.sgreben.regex_builder.FluentRe;
```

```java
CaptureGroup ip, client, user, dateTime, method, request, protocol, responseCode, size;
FluentRe nonWhitespace = FluentRe.match(nonWhitespaceChar()).repeat1();

ip = nonWhitespace.capture();
client = nonWhitespace.capture();
user = nonWhitespace.capture();
dateTime = FluentRe
    .match(union(wordChar(), oneOf(":/"))).repeat1()
    .then(whitespaceChar())
    .then(oneOf("+\\-"))
    .then(FluentRe.match(digit()).repeat(4))
    .capture();
method = nonWhitespace.capture();
request = nonWhitespace.capture();
protocol = nonWhitespace.capture();
responseCode = FluentRe.match(digit()).repeat(3).capture();
size = FluentRe.match(digit()).repeat1().capture();

Pattern p = FluentRe.match(beginInput())
    .then(ip).then(' ')
    .then(client).then(' ')
    .then(user).then(" [")
    .then(dateTime).then("] \"")
    .then(method).then(' ')
    .then(request).then(' ')
    .then(protocol).then("\" ")
    .then(responseCode).then(' ')
    .then(size)
    .then(endInput())
    .compile();
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

## Reusing expressions

To reuse an expression cleanly, it should be packaged as a class. To access the capture groups contained in the expression,
each capture group should be exposed as a final field or method.

To allow the resulting object to be used as an expression, `regex-builder` provides a utility class `ExpressionWrapper`,
which exposes a method `setExpression(Expression expr)` and implements the `Expresssion` interface.

```java
import com.github.sgreben.regex_builder.ExpressionWrapper;
```

To use the class, simply extend it and call `setExpression` in your constructor or initialization block.
You can then pass it to any `regex-builder` method that expects an `Expression`.

### Reusable Apache log expression
Using `ExpressionWrapper`, we can package the Apache log
example above as follows:
```java
public class ApacheLog extends ExpressionWrapper {
    public final CaptureGroup ip, client, user, dateTime, method, request, protocol, responseCode, size;

    {
        Expression nonWhitespace = repeat1(CharClass.nonWhitespaceChar());
        ip = capture(nonWhitespace);
        client = capture(nonWhitespace);
        user = capture(nonWhitespace);
        dateTime = capture(sequence(
            repeat1(union(wordChar(), ':', '/')),
            whitespaceChar(),
            oneOf("+\\-"),
            repeat(digit(), 4)
        ));
        method = capture(nonWhitespace);
        request = capture(nonWhitespace);
        protocol = capture(nonWhitespace);
        responseCode = capture(repeat(CharClass.digit(), 3));
        size = capture(repeat1(CharClass.digit()));

        Expression expression = sequence(
            ip, ' ', client, ' ', user, " [", dateTime, "] \"", method, ' ', request, ' ', protocol, "\" ", responseCode, ' ', size,
        );
        setExpression(expression);
    }
}
```

We can then use instances of the packaged expression like this:

```java
public static boolean sameIP(String twoLogs) {
    ApacheLog log1 = new ApacheLog();
    ApacheLog log2 = new ApacheLog();
    Pattern p = Pattern.compile(sequence(
        log1, ' ', log2
    ));
    Matcher m = p.matcher(twoLogs);
    m.find();
    return m.group(log1.ip).equals(m.group(log2.ip));
}
```


## API

### Expression builder

| Builder method              | `java.util.regex` syntax |
| --------------------------- | ------------------------ |
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

| Builder method                | `java.util.regex` syntax |
| ----------------------------- | ------------------------ |
| range(from, to)               | [from-to]                |
| range(f1, t1, ..., fN, tN)    | [f1-t1f2-t2...fN-tN]     |
| oneOf("abcde")                | [abcde]                  |
| union(class1, ..., classN)    | [[class1]...[classN]]    |
| complement(class1)            | [\^[class1]]             |
| anyChar()                     | .                        |
| digit()                       | \d                       |
| nonDigit()                    | \D                       |
| hexDigit()                    | [a-fA-F0-9]              |
| nonHexDigit()                 | [\^[a-fA-F0-9]]          |
| wordChar()                    | \w                       |
| nonWordChar()                 | \W                       |
| wordBoundary()                | \b                       |
| nonWordBoundary()             | \B                       |
| whitespaceChar()              | \s                       |
| nonWhitespaceChar()           | \S                       |
| verticalWhitespaceChar()      | \v                       |
| nonVerticalWhitespaceChar()   | \V                       |
| horizontalWhitespaceChar()    | \h                       |
| nonHorizontalWhitespaceChar() | \H                       |
