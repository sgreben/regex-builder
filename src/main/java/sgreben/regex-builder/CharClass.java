package sgreben.regex_builder;

import sgreben.regex_builder.charclass.*;
import sgreben.regex_builder.tokens.TOKEN;

public abstract class CharClass {
	public abstract java.lang.Iterable<CharClass> children();
	public abstract void accept(CharClassVisitor visitor);
	public abstract void compile(java.util.List<TOKEN> output);
	
	public static CharClass range(char from, char to) {
		return new Range(from, to);
	}
	public static CharClass range(char... ranges) {
		return new Range(ranges);
	}
	public static CharClass union(CharClass... cs) {
		return new Union(cs);
	}
	public static CharClass union(Object... cs) {
		return union(convertStrings(cs));
	}
	public static CharClass intersection(CharClass... cs) {
		return new Intersection(cs);
	}
	public static CharClass intersection(Object... cs) {
		return intersection(convertStrings(cs));
	}
	public static CharClass complement(CharClass cs) {
		return new Complement(cs);
	}
	public static CharClass complement(Object... cs) {
		return complement(convertStrings(cs));
	}
	public static CharClass anyChar() {
		return new AnyCharacter();
	}
	public static CharClass digit() {
		return new Digit();
	}
	public static CharClass nonDigit() {
		return new NonDigit();
	}
	public static CharClass hexDigit() {
		return range('a','f', 'A','F', '0','9');
	}
	public static CharClass nonHexDigit() {
		return complement(hexDigit());
	} 
	public static CharClass wordChar() {
		return new WordCharacter();
	}
	public static CharClass nonWordChar() {
		return new NonWordCharacter();
	}
	public static CharClass wordBoundary() {
		return new WordBoundary();
	}
	public static CharClass nonWordBoundary() {
		return new NonWordBoundary();
	}
	public static CharClass whitespaceChar() {
		return new Whitespace();
	}
	public static CharClass nonWhitespaceChar() {
		return new NonWhitespace();
	}
	public static CharClass verticalWhitespaceChar() {
		return new VerticalWhitespace();
	}
	public static CharClass nonVerticalWhitespaceChar() {
		return new NonVerticalWhitespace();
	}
	public static CharClass horizontalWhitespaceChar() {
		return new HorizontalWhitespace();
	}
	public static CharClass nonHorizontalWhitespaceChar() {
		return new NonHorizontalWhitespace();
	}
	public static CharClass beginInput() {
		return new BeginInput();
	}
	public static CharClass endInput() {
		return new EndInput();
	}
	public static CharClass endInputBeforeFinalTerminator() {
		return new EndInputBeforeFinalTerminator();
	}
	public static CharClass oneOf(String chars) {
		return new OneOf(chars);
	}
	private static CharClass[] convertStrings(Object[] os) {
		CharClass[] charClasses = new CharClass[os.length];
		for(int i = 0; i < os.length; ++i) {
			Object o = os[i];
			if(o instanceof Character) {
				charClasses[i] = oneOf(""+(Character)o);
			}
			if(o instanceof String) {
				charClasses[i] = oneOf((String)o);
			}
			if(o instanceof CharClass) {
				charClasses[i] = (CharClass) o;
			}
		}
		return charClasses;
	}
}