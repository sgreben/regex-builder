package com.github.sgreben.regex_builder;

import static com.github.sgreben.regex_builder.CharClass.complement;
import static com.github.sgreben.regex_builder.CharClass.hexDigit;
import static com.github.sgreben.regex_builder.CharClass.nonHexDigit;
import static com.github.sgreben.regex_builder.CharClass.noneOf;
import static com.github.sgreben.regex_builder.CharClass.oneOf;
import static com.github.sgreben.regex_builder.CharClass.whitespaceChar;
import static com.github.sgreben.regex_builder.Re.repeat;
import static com.github.sgreben.regex_builder.hamcrest.MatchesPattern.matchesPattern;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class CharClassTest {

	@Test
	public void testHexDigit() throws Exception {
		assertCharClassMatch(hexDigit(), "0123456789");
		assertCharClassMatch(hexDigit(), "abcdef");
		assertCharClassMatch(hexDigit(), "ABCDEF");

		assertCharClassMismatch(hexDigit(), "G");
		assertCharClassMismatch(hexDigit(), "[");
		assertCharClassMismatch(hexDigit(), "]");
	}

	@Test
	public void testNonHexDigit() throws Exception {
		assertCharClassMismatch(nonHexDigit(), "0123456789");
		assertCharClassMismatch(nonHexDigit(), "abcdef");
		assertCharClassMismatch(nonHexDigit(), "ABCDEF");

		assertCharClassMatch(nonHexDigit(), "G");
		assertCharClassMatch(nonHexDigit(), "[");
		assertCharClassMatch(nonHexDigit(), "]");
	}

	@Test
	public void testComplement() throws Exception {
		assertCharClassMismatch(complement(oneOf("abc")), "abc");
		assertCharClassMatch(complement(oneOf("abc")), "xyz");

		assertCharClassMismatch(complement(whitespaceChar()), "  ");
		assertCharClassMatch(complement(whitespaceChar()), "123");

		assertCharClassMismatch(complement(oneOf("bc")), "bcbc");
		assertCharClassMatch(complement(complement(oneOf("bc"))), "bcbc");
	}

	@Test
	public void testOneOf() throws Exception {
		assertCharClassMatch(oneOf("abc"), "abc");
		assertCharClassMismatch(oneOf("def"), "abc");
	}

	@Test
	public void testNoneOf() throws Exception {
		assertCharClassMatch(noneOf("def"), "abc");
		assertCharClassMismatch(noneOf("abc"), "abc");
	}

	private void assertCharClassMatch(CharClass charClass, String example) {
		assertThat(example, matchesPattern(Pattern.compile(repeat(charClass))));
	}

	private void assertCharClassMismatch(CharClass charClass, String example) {
		assertThat(example, not(matchesPattern(Pattern.compile(repeat(charClass)))));
	}

}
