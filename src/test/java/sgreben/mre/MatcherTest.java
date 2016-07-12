package sgreben.mre;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import sgreben.mre.CaptureGroup;
import sgreben.mre.Captured;
import sgreben.mre.Matcher;
import sgreben.mre.expression.Expression;
import sgreben.mre.Mre;

public class MatcherTest {
	@Test
	public void matchNumber_matchedIsTrue() {
		String s = "123";
		Pattern p = Mre.compile(Mre.number());
		Matcher m = p.matcher(s);
		assertTrue(m.matches());
	}
	
	@Test
	public void matchAnyNumberAny_matchedIsTrue() {
		String s = "abc 123 def";
		Expression nonNumbers = Mre.many(Mre.nonDigit()); 
		Pattern p = Mre.compile(Mre.sequence(
			nonNumbers,
			Mre.number(),
			nonNumbers
		));
		Matcher m = p.matcher(s);
		assertTrue(m.matches());
	}

	@Test
	public void matchNumberCaptureNumber_returnsNumber() {
		String s = "123";
		CaptureGroup number = Mre.capture(Mre.number());
		Pattern p = Mre.compile(number);
		Matcher m = p.matcher(s);
		m.matches();
		assertEquals("123", m.group(number));
	}

	@Test
	public void matchAnyNumberAnyCaptureNumber_returnsNumber() {
		String s = "abc 123 def";
		CaptureGroup number = Mre.capture(Mre.number());
		Expression nonNumbers = Mre.many(Mre.nonDigit()); 
		Pattern p = Mre.compile(Mre.sequence(
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
		CaptureGroup number = Mre.capture(Mre.number());
		Pattern p = Mre.compile(
			Mre.sequence(
				number,
				Mre.optional(Mre.whitespace())
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
		CaptureGroup word = Mre.capture(
			Mre.word()
		);
		CaptureGroup sentence = Mre.capture(
			Mre.sequence(
				Mre.separatedBy(Mre.whitespace(), word),
				Mre.character('.')
			)
		);
		Pattern p = Mre.compile(
			Mre.sequence(sentence, Mre.optional(Mre.whitespace()))
		);
		Matcher m = p.matcher(s);
		m.find();
		assertEquals("There are things.", m.group(sentence));
		m.find();
		assertEquals("Things have properties.", m.group(sentence));	
	}
}
