package sgreben.regex_builder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
}
