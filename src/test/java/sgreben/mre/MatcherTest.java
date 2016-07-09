package sgreben.mre;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import sgreben.mre.CaptureGroup;
import sgreben.mre.Captured;
import sgreben.mre.Matcher;
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
		Pattern p = Mre.compile(Mre.sequence(
			Mre.anyCharacter(),
			Mre.number(),
			Mre.anyCharacter()
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
		Captured captured = number.getCaptured();
		assertEquals(1, captured.length());
		assertEquals("123", captured.getString(0));
	}

	@Test
	public void matchAnyNumberAnyCaptureNumber_returnsNumber() {
		String s = "abc 123 def";
		CaptureGroup number = Mre.capture(Mre.number());
		Pattern p = Mre.compile(Mre.sequence(
			Mre.anyCharacter(),
			number,
			Mre.anyCharacter()
		));
		Matcher m = p.matcher(s);
		Captured captured = number.getCaptured();
		assertEquals(1, captured.length());
		assertEquals("123", captured.getString(0));
	}

	@Test
	public void matchNumbers_separatedBySpaces() {
		String s = "123 456 789";
		CaptureGroup number = Mre.capture(Mre.number());
		Pattern p = Mre.compile(
			Mre.separatedBy(Mre.whitespace(), number)
		);
		Matcher m = p.matcher(s);
		Captured captured = number.getCaptured();
		assertEquals(3, captured.length());
		assertEquals("123", captured.getString(0));
		assertEquals("456", captured.getString(1));
		assertEquals("789", captured.getString(3));
	}
	
	@Test
	public void nestedCapture_returnsBoth() {
		String s = "There are things. Things have properties.";
		CaptureGroup word = Mre.capture(
			Mre.word()
		);
		CaptureGroup sentence = Mre.capture(Mre.sequence(
			Mre.separatedBy(Mre.whitespace(), word),
			Mre.character('.'), 
			Mre.optional(Mre.whitespace())
		));
		Pattern p = Mre.compile(Mre.many(sentence));
		Matcher m = p.matcher(s);
		Captured sentences = sentence.getCaptured();
		Captured words = word.getCaptured();
		assertEquals(2, sentences.length());
		assertEquals(6, words.length());
		assertEquals("There are things. ", sentences.getString(0));
		assertEquals("Things have properties.", sentences.getString(1));
		Captured sentence1 = sentences.get(0);
		assertEquals("There", sentence1.getNested(word).getString(0));
		assertEquals("are", sentence1.getNested(word).getString(1));
		assertEquals("things", sentence1.getNested(word).getString(2));
		Captured sentence2 = sentences.get(1);
		assertEquals("Things", sentence2.getNested(word).getString(0));
		assertEquals("have", sentence2.getNested(word).getString(1));
		assertEquals("properties", sentence2.getNested(word).getString(2));	
	}
}
