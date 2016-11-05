package sgreben.regex_builder;

import static org.junit.Assert.*;
import static sgreben.regex_builder.CharClass.*;
import static sgreben.regex_builder.Re.*;
import org.junit.Test;

public class MatcherTest {
	@Test
	public void matchNumber_matchedIsTrue() {
		String s = "123";
		Pattern p = Pattern.compile(Re.number());
		Matcher m = p.matcher(s);
		assertTrue(m.matches());
	}
	
	@Test
	public void matchNumber_matchedIsTrue_static() {
		String s = "123";
		assertTrue(Pattern.matches(Re.number(), s));
	}
	
	@Test
	public void matchNumber_matchedIsFalse_static() {
		String s = "abc";
		assertFalse(Pattern.matches(Re.number(), s));
	}

	@Test
	public void matchAnyNumberAny_matchedIsTrue() {
		String s = "abc 123 def";
		Expression nonNumbers = Re.repeat(nonDigit());
		Pattern p = Pattern.compile(Re.sequence(
			nonNumbers,
			Re.number(),
			nonNumbers
		));
		Matcher m = p.matcher(s);
		assertTrue(m.matches());
	}

	@Test
	public void matchNumberCaptureNumber_returnsNumber() {
		String s = "123";
		CaptureGroup number = Re.capture(Re.number());
		Pattern p = Pattern.compile(number);
		Matcher m = p.matcher(s);
		m.matches();
		assertEquals("123", m.group(number));
	}

	@Test
	public void matchAnyNumberAnyCaptureNumber_returnsNumber() {
		String s = "abc 123 def";
		CaptureGroup number = Re.capture(Re.number());
		Expression nonNumbers = Re.repeat(nonDigit());
		Pattern p = Pattern.compile(Re.sequence(
			nonNumbers,
			number,
			nonNumbers
		));
		Matcher m = p.matcher(s);
		m.matches();
		assertEquals("123", m.group(number));
	}

	@Test
	public void matchNumbers_separatedBySpaces() {
		String s = "123 456 789";
		CaptureGroup number = Re.capture(Re.number());
		Pattern p = Pattern.compile(
			Re.sequence(
				number,
				Re.optional(Re.whitespace())
			)
		);
		Matcher m = p.matcher(s);
		m.find();
		assertEquals("123", m.group(number));
		m.find();
		assertEquals("456", m.group(number));
		m.find();
		assertEquals("789", m.group(number));
	}
	
	@Test
	public void matchNumbers_replaceByParenthesized() {
		String s = "123 456 789";
		CaptureGroup number = Re.capture(Re.number());
		Pattern p = Pattern.compile(
			Re.sequence(
				number,
				Re.optional(Re.whitespace())
			)
		);
		Matcher m = p.matcher(s);
		String result = m.replaceAll(Re.replacement("(number ",number,")"));
		assertEquals("(number 123)(number 456)(number 789)", result);
	}

	@Test
	public void matchWords_replaceByDoubled() {
		String s = "abc def ghi";
		CaptureGroup word = Re.capture(Re.word());
		Pattern p = Pattern.compile(word);
		Matcher m = p.matcher(s);
		String result = m.replaceAll(Re.replacement(word, word));
		assertEquals("abcabc defdef ghighi", result);
	}
	
	@Test
	public void matchChar_replaceByDoubled() {
		String s = "abc def ghi";
		CaptureGroup b = Re.capture(Re.character('b'));
		Pattern p = Pattern.compile(b);
		Matcher m = p.matcher(s);
		String result = m.replaceAll(Re.replacement("<", b, b, ">"));
		assertEquals("a<bb>c def ghi", result);
	}
	
	@Test
	public void matchChar_literalSyntaxString_replaceByDoubled() {
		String s = "abc def ghi";
		CaptureGroup b = Re.capture("b");
		Pattern p = Pattern.compile(b);
		Matcher m = p.matcher(s);
		String result = m.replaceAll(Re.replacement("<", b, b, ">"));
		assertEquals("a<bb>c def ghi", result);
	}
	
	@Test
	public void matchWithBackReference_capturesCorrectly() {
		String s = "abc abc def";
		CaptureGroup word = Re.capture(Re.word());
		CaptureGroup sameWordTwice = Re.capture(
			Re.sequence(word, Re.whitespace1(), Re.backReference(word))
		);
		Pattern p = Pattern.compile(sameWordTwice);
		Matcher m = p.matcher(s);
		m.find();
		assertEquals("abc abc", m.group(sameWordTwice));
		assertEquals("abc", m.group(word));
	}

	@Test
	public void matchChar_literalSyntaxChar_replaceByDoubled() {
		String s = "abc def ghi";
		CaptureGroup b = Re.capture('b');
		Pattern p = Pattern.compile(b);
		Matcher m = p.matcher(s);
		String result = m.replaceAll(Re.replacement('<', b, b, '>'));
		assertEquals("a<bb>c def ghi", result);
	}

	@Test
	public void matchCharTwoGroup_replaceByDoubled() {
		String s = "abc def ghi";
		CaptureGroup a = Re.capture(Re.character('a'));
		CaptureGroup b = Re.capture(Re.character('b'));
		Pattern p = Pattern.compile(Re.sequence(a, b));
		Matcher m = p.matcher(s);
		String result = m.replaceAll(Re.replacement("<", b, b, ">"));
		assertEquals("<bb>c def ghi", result);
	}
	
	@Test
	public void matchChars_literalSyntax_replaceByDoubled() {
		String s = "abc def ghi";
		CaptureGroup a = Re.capture("a");
		CaptureGroup b = Re.capture("b");
		Pattern p = Pattern.compile(Re.sequence(a, b, "c"));
		Matcher m = p.matcher(s);
		String result = m.replaceAll(Re.replacement("<", b, b, ">"));
		assertEquals("<bb> def ghi", result);
	}

	@Test
	public void nestedCapture_returnsBoth() {
		String s = "There are things. Things have properties.";
		CaptureGroup word = Re.capture(Re.word());
		CaptureGroup sentence = Re.capture(
			Re.sequence(
				Re.separatedBy(Re.whitespace(), word),
				Re.character('.')
			)
		);
		Pattern p = Pattern.compile(
			Re.sequence(sentence, Re.optional(Re.whitespace()))
		);
		Matcher m = p.matcher(s);
		m.find();
		assertEquals("There are things.", m.group(sentence));
		m.find();
		assertEquals("Things have properties.", m.group(sentence));	
	}
	
	@Test
	public void hexColorExampleFromReadme() {
		Expression hexDigit = Re.charClass(CharClass.union(
			CharClass.range('a','f'),
			CharClass.range('A','F'),
			CharClass.digit()
		));
		Expression threeHexDigits = Re.repeat(hexDigit, 3);
		CaptureGroup hexValue = Re.capture(
			threeHexDigits,              // #FFF  
			Re.optional(threeHexDigits)  // #FFFFFF
	    );
		Expression hexColor = Re.sequence('#', hexValue);
		Pattern p = Pattern.compile(hexColor);
		Matcher m = p.matcher("#0FAFF3 and #1bf");
		m.find();
		assertEquals("0FAFF3", m.group(hexValue));
		m.find();
		assertEquals("1bf", m.group(hexValue));
	}
	@Test
	public void hexColorExampleFromReadme_alternativeBuild() {
		Expression hexDigit = Re.charClass(CharClass.range(
			'a','f', 'A','F', '0','9'
		));
		Expression threeHexDigits = Re.repeat(hexDigit, 3);
		CaptureGroup hexValue = Re.capture(Re.sequence(
			threeHexDigits,              // #FFF  
			Re.optional(threeHexDigits)  // #FFFFFF
	    ));
		Expression hexColor = Re.sequence('#', hexValue);
		Pattern p = Pattern.compile(hexColor);
		Matcher m = p.matcher("#0FAFF3 and #1bf");
		m.find();
		assertEquals("0FAFF3", m.group(hexValue));
		m.find();
		assertEquals("1bf", m.group(hexValue));
	}
	@Test
	public void hexColorExampleFromReadme_alternativeBuildUsingBuiltinHexdigit() {
		Expression hexDigit = Re.hexDigit();
		Expression threeHexDigits = Re.repeat(hexDigit, 3);
		CaptureGroup hexValue = Re.capture(Re.sequence(
			threeHexDigits,              // #FFF  
			Re.optional(threeHexDigits)  // #FFFFFF
	    ));
		Expression hexColor = Re.sequence('#', hexValue);
		Pattern p = Pattern.compile(hexColor);
		Matcher m = p.matcher("#0FAFF3 and #1bf");
		m.find();
		assertEquals("0FAFF3", m.group(hexValue));
		m.find();
		assertEquals("1bf", m.group(hexValue));
	}
	@Test
	public void possessiveQualifierTest() {
		Expression xxy = Re.sequence(
			Re.repeatPossessive(Re.sequence(
				Re.repeatPossessive('x'),
				Re.repeatPossessive('x')
			)),
			'y'
		);
		Pattern p = Pattern.compile(xxy);
		Matcher m = p.matcher("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		assertEquals(false, m.matches());
	}
	
	@Test
	public void possessiveQualifierTest_positive() {
		Expression xxy = Re.sequence(
			Re.repeat(Re.sequence(
				Re.repeat('x').possessive(),
				Re.repeat('x').possessive()
			)).possessive(),
			'y'
		);
		Pattern p = Pattern.compile(xxy);
		Matcher m = p.matcher("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxy");
		assertEquals(true, m.matches());
	}
	
	@Test
	public void charClassIntersectionTest() {
		Expression d = Re.charClass(CharClass.intersection(
			CharClass.oneOf("abcd"),
			CharClass.oneOf("defg"),
			CharClass.wordChar()
		));
		Pattern p = Pattern.compile(d);
		Matcher m1 = p.matcher("a");
		Matcher m2 = p.matcher("d");
		assertFalse(m1.matches());
		assertTrue(m2.matches());
		
	}
	@Test
	public void positiveLookaheadTest() {
		Expression abc = Re.sequence(
			"abc", Re.positiveLookahead("def")
		);
		Pattern p = Pattern.compile(abc);
		assertFalse(p.matcher("123abc").find());
		assertTrue(p.matcher("123abcdef").find());
		assertFalse(p.matcher("123abc123def").find());
		assertTrue(p.matcher("abcdef123").find());
	}
	@Test
	public void positiveLookbehindTest() {
		Expression abc = Re.sequence(
			Re.positiveLookbehind("def"), "abc"
		);
		Pattern p = Pattern.compile(abc);
		assertFalse(p.matcher("def123abc").find());
		assertTrue(p.matcher("123defabc").find());
		assertFalse(p.matcher("def123abc123def").find());
		assertTrue(p.matcher("defabc123").find());
	}
	@Test
	public void negativeLookbehindTest() {
		Expression abc = Re.sequence(
			Re.negativeLookbehind("def"), "abc"
		);
		Pattern p = Pattern.compile(abc);
		assertTrue(p.matcher("def123abc").find());
		assertFalse(p.matcher("123defabc").find());
		assertTrue(p.matcher("def123abc123def").find());
		assertFalse(p.matcher("defabc123").find());
	}
	@Test
	public void negativeLookaheadTest() {
		Expression abc = Re.sequence(
			"abc", Re.negativeLookahead("def")
		);
		Pattern p = Pattern.compile(abc);
		assertTrue(p.matcher("123abc").find());
		assertFalse(p.matcher("123abcdef").find());
		assertTrue(p.matcher("123abc123def").find());
		assertFalse(p.matcher("abcdef123").find());
	}

	@Test
	public void apacheLogLine() {
		String logLine = "127.0.0.1 - - [21/Jul/2014:9:55:27 -0800] \"GET /home.html HTTP/1.1\" 200 2048";
		// "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+)";

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
	}
}
