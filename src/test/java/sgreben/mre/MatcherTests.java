import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import sgreben.mre.CaptureGroup;
import sgreben.mre.Captured;
import sgreben.mre.Mre;

public class MatcherTests {
	@Test
	public void matchNumber_matchedIsTrue() {
		String s = "123";
		Matcher m = Mre.compile(Mre.number());
		m.read(s);
		assertTrue(m.hasMatched());
	}
	
	@Test
	public void matchAnyNumberAny_matchedIsTrue() {
		String s = "abc 123 def";
		Matcher m = Mre.compile(Mre.sequence(
			Mre.anyChar(),
			Mre.number(),
			Mre.anyChar()
		));
		m.read(s);
		assertTrue(m.hasMatched());
	}

	@Test
	public void matchNumberCaptureNumber_returnsNumber() {
		String s = "123";
		CaptureGroup number = Mre.capture(Mre.number());
		Matcher m = Mre.compile(number);
		m.read(s);
		Captured captured = number.getCaptured();
		assertEquals(1, captured.length());
		assertEquals("123", captured.getString(0));
	}

	@Test
	public void matchAnyNumberAnyCaptureNumber_returnsNumber() {
		String s = "abc 123 def";
		CaptureGroup number = Mre.capture(Mre.number());
		Matcher m = Mre.compile(Mre.sequence(
			Mre.anyChar(),
			number,
			Mre.anyChar()
		));
		m.read(s);
		Captured captured = number.getCaptured();
		assertEquals(1, captured.length());
		assertEquals("123", captured.getString(0));
	}

	@Test
	public void matchNumbers_separatedBySpaces() {
		String s = "123 456 789";
		CaptureGroup number = Mre.capture(Mre.number());
		Matcher m = Mre.compile(
			Mre.separatedBy(Mre.whitespace(), number)
		);
		m.read(s);
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
		Matcher m = Mre.compile(Mre.many(sentence));
		m.read(s);
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
