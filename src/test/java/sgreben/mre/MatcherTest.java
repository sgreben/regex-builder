package sgreben.regex_builder;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import sgreben.regex_builder.CaptureGroup;
import sgreben.regex_builder.Captured;
import sgreben.regex_builder.Matcher;
import sgreben.regex_builder.expression.Expression;
import sgreben.regex_builder.Re;

public class MatcherTest {
	@Test
	public void matchNumber_matchedIsTrue() {
		String s = "123";
		Pattern p = Re.compile(Re.number());
		Matcher m = p.matcher(s);
		assertTrue(m.matches());
	}
	
	@Test
	public void matchAnyNumberAny_matchedIsTrue() {
		String s = "abc 123 def";
		Expression nonNumbers = Re.many(Re.nonDigit()); 
		Pattern p = Re.compile(Re.sequence(
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
		Pattern p = Re.compile(number);
		Matcher m = p.matcher(s);
		m.matches();
		assertEquals("123", m.group(number));
	}

	@Test
	public void matchAnyNumberAnyCaptureNumber_returnsNumber() {
		String s = "abc 123 def";
		CaptureGroup number = Re.capture(Re.number());
		Expression nonNumbers = Re.many(Re.nonDigit()); 
		Pattern p = Re.compile(Re.sequence(
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
		Pattern p = Re.compile(
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
	public void nestedCapture_returnsBoth() {
		String s = "There are things. Things have properties.";
		CaptureGroup word = Re.capture(
			Re.word()
		);
		CaptureGroup sentence = Re.capture(
			Re.sequence(
				Re.separatedBy(Re.whitespace(), word),
				Re.character('.')
			)
		);
		Pattern p = Re.compile(
			Re.sequence(sentence, Re.optional(Re.whitespace()))
		);
		Matcher m = p.matcher(s);
		m.find();
		assertEquals("There are things.", m.group(sentence));
		m.find();
		assertEquals("Things have properties.", m.group(sentence));	
	}
}
