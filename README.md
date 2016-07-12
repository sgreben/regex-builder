# Regex Builder for Java

Write regexes as **plain Java code**. Unlike opaque regex strings, commenting your expressions and reusing regex fragments is straightforward.

The **regex-builder** library is implemented as a light-weight wrapper around `java.util.regex`.

## Examples

Imports:
```java
import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.Expression;
import sgreben.regex_builder.Re;
```

### Hex color

- Regex string: `^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$`
- Java code:
```java
Expression hexDigit = Re.charClass("[a-fA-F0-9]");
Expression threeHexDigits = Re.repeat(hexDigit, 3);
CaptureGroup hexValue = Re.capture(
  Re.sequence(
    threeHexDigits, Re.optional(threeHexDigits),
  )
);
Expression hexColor = Re.sequence(
  Re.beginLine(), 
  Re.character('#'), hexValue, // #FFF or #FFFFFF 
  Re.endLine()
);
```

Use the expression like this:
```java
Pattern p = Re.compile(hexColor)
Matcher m = p.matcher("#0FAFF3 and #1bf");
m.find();
assertEquals("0FAFF3", m.group(hexValue));
m.find();
assertEquals("1bf", m.group(hexValue));
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
```


Use the expression like this:
```java
Pattern p = Re.compile(dateExpression)
Matcher m = p.matcher("01/05/2015 12:30:22");
m.find();
assertEquals("01", m.group(day));
assertEquals("05", m.group(month));
assertEquals("2015", m.group(year));
assertEquals("12", m.group(hour));
assertEquals("30", m.group(minute));
assertEquals("22", m.group(second));
```
