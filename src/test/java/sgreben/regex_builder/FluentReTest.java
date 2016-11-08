package sgreben.regex_builder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sgreben.regex_builder.CharClass.*;
import static sgreben.regex_builder.Re.replacement;

public class FluentReTest {
    @Test
    public void aOrB() {
        Pattern p = FluentRe.match('a').or('B').compile();
        assertEquals("((?:\\Qa\\E|\\QB\\E))", p.toString());
    }

    @Test
    public void optionalAOrB() {
        Pattern p = FluentRe.match('A').or('B').optional().compile();
        assertEquals("((?:(?:\\QA\\E|\\QB\\E))?)", p.toString());
    }

    @Test
    public void aOrBOrC() {
        Pattern p = FluentRe.match('a').or('B').or('C').compile();
        assertEquals("((?:(?:\\Qa\\E|\\QB\\E)|\\QC\\E))", p.toString());
    }

    @Test
    public void abcRepeat() {
        Pattern p = FluentRe.match("abc").repeat().compile();
        assertEquals("((?:\\Qabc\\E)*)", p.toString());
    }

    @Test
    public void abcRepeatN() {
        Pattern p = FluentRe.match("abc").repeat(123).compile();
        assertEquals("((?:\\Qabc\\E){123})", p.toString());
    }

    @Test
    public void abcRepeatNM() {
        Pattern p = FluentRe.match("abc").repeat(123, 456).compile();
        assertEquals("((?:\\Qabc\\E){123,456})", p.toString());
    }

    @Test
    public void abcThenDef() {
        Pattern p = FluentRe.match("abc").then("Def").compile();
        assertEquals("(\\Qabc\\E\\QDef\\E)", p.toString());
    }

    @Test
    public void abcRepeatAtLeast3() {
        Pattern p = FluentRe.match("abc").repeatAtLeast(3).compile();
        assertEquals("((?:\\Qabc\\E){3,})", p.toString());
    }

    @Test
    public void abcThenDefOrGhi() {
        Pattern p = FluentRe.match("abc").then("Def").or("Ghi").compile();
        assertEquals("((?:\\Qabc\\E\\QDef\\E|\\QGhi\\E))", p.toString());
    }

    @Test
    public void abcThen_DefOrGhi() {
        Pattern p = FluentRe.match("abc").then(FluentRe.match("Def").or("Ghi")).compile();
        assertEquals("(\\Qabc\\E(?:\\QDef\\E|\\QGhi\\E))", p.toString());
    }

    @Test
    public void abcThen_DefOrGhiGroup() {
        CaptureGroup abcOrGhiGroup = FluentRe.match("Def").or("Ghi").capture();
        Pattern p = FluentRe.match("abc").then(abcOrGhiGroup).compile();
        assertEquals("(\\Qabc\\E((?:\\QDef\\E|\\QGhi\\E)))", p.toString());
    }

    @Test
    public void apacheLogLine() {
        String logLine = "127.0.0.1 - - [21/Jul/2014:9:55:27 -0800] \"GET /home.html HTTP/1.1\" 200 2048";
        // "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+)";

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
        assertEquals("127.0.0.1 - /home.html - 200", m.replaceAll(replacement(ip, " - ", request, " - ", responseCode)));
        assertEquals("127.0.0.1 - /home.html - 200", m.replaceFirst(replacement(ip, " - ", request, " - ", responseCode)));
    }
}
