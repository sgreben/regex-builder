package sgreben.regex_builder;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.Captured;
import sgreben.regex_builder.Matcher;
import sgreben.regex_builder.expression.Expression;
import sgreben.regex_builder.RegexBuilder;

public class MatcherTest {
	@Test
	public void matchNumber_matchedIsTrue() {
		String s = "123";
		Pattern p = RegexBuilder.compile(RegexBuilder.number());
		Matcher m = p.matcher(s);
		assertTrue(m.matches());
	}
	
	@Test
	public void matchAnyNumberAny_matchedIsTrue() {
		String s = "abc 123 def";
		Expression nonNumbers = RegexBuilder.many(RegexBuilder.nonDigit()); 
		Pattern p = RegexBuilder.compile(RegexBuilder.sequence(
			nonNumbers,
			RegexBuilder.number(),
			nonNumbers
		));
		Matcher m = p.matcher(s);
		assertTrue(m.matches());
	}

	@Test
	public void matchNumberCaptureNumber_returnsNumber() {
		String s = "123";
		CaptureGroup number = RegexBuilder.capture(RegexBuilder.number());
		Pattern p = RegexBuilder.compile(number);
		Matcher m = p.matcher(s);
		m.matches();
		assertEquals("123", m.group(number));
	}

	@Test
	public void matchAnyNumberAnyCaptureNumber_returnsNumber() {
		String s = "abc 123 def";
		CaptureGroup number = RegexBuilder.capture(RegexBuilder.number());
		Expression nonNumbers = RegexBuilder.many(RegexBuilder.nonDigit()); 
		Pattern p = RegexBuilder.compile(RegexBuilder.sequence(
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
		CaptureGroup number = RegexBuilder.capture(RegexBuilder.number());
		Pattern p = RegexBuilder.compile(
			RegexBuilder.sequence(
				number,
				RegexBuilder.optional(RegexBuilder.whitespace())
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
	public void nestedCapture_returnsBoth() {
		String s = "There are things. Things have properties.";
		CaptureGroup word = RegexBuilder.capture(
			RegexBuilder.word()
		);
		CaptureGroup sentence = RegexBuilder.capture(
			RegexBuilder.sequence(
				RegexBuilder.separatedBy(RegexBuilder.whitespace(), word),
				RegexBuilder.character('.')
			)
		);
		Pattern p = RegexBuilder.compile(
			RegexBuilder.sequence(sentence, RegexBuilder.optional(RegexBuilder.whitespace()))
		);
		Matcher m = p.matcher(s);
		m.find();
		assertEquals("There are things.", m.group(sentence));
		m.find();
		assertEquals("Things have properties.", m.group(sentence));	
	}
}
