package com.github.sgreben.regex_builder;

import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import static com.github.sgreben.regex_builder.hamcrest.MatchesPattern.*;
import static com.github.sgreben.regex_builder.Re.*;
import static com.github.sgreben.regex_builder.CharClass.*;

import static org.hamcrest.CoreMatchers.*;

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

	@Ignore
	@Test
	public void testNonHexDigit() throws Exception {
		assertCharClassMismatch(nonHexDigit(), "0123456789");
		assertCharClassMismatch(nonHexDigit(), "abcdef");
		assertCharClassMatch(nonHexDigit(), "ABCDEF");

		assertCharClassMatch(nonHexDigit(), "G");
		assertCharClassMatch(nonHexDigit(), "[");
		assertCharClassMatch(nonHexDigit(), "]");
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
