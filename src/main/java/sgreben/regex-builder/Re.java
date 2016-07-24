package sgreben.regex_builder;

import sgreben.regex_builder.expression.*;
import sgreben.regex_builder.compiler.Compiler;
import sgreben.regex_builder.CharClass;

public class Re {
	public static Expression string(String s) {
		return new Literal(s);
	}
	public static Expression character(char c) {
		return string(""+c);
	}
	public static Expression beginInput() {
		return new Raw("\\A");
	}
	public static Expression endInput() {
		return new Raw("\\z");
	}
	public static Expression endInputBeforeFinalTerminator() {
		return new Raw("\\Z");
	}
	public static Expression beginLine() {
		return new Raw("^");
	}
	public static Expression endLine() {
		return new Raw("$");
	}
	public static Expression charClass(CharClass charClass) {
		return new CharClassExpression(charClass);
	}
	public static Expression anyCharacter() {
		return charClass(CharClass.anyChar());
	}
	public static Expression hexDigit() {
		return charClass(CharClass.hexDigit());
	}
	public static Expression nonHexDigit() {
		return charClass(CharClass.complement(CharClass.hexDigit()));
	}
	public static Expression digit() {
		return charClass(CharClass.digit());
	}
	public static Expression nonDigit() {
		return charClass(CharClass.nonDigit());
	}
	public static Expression many(Expression e) {
		return new Many(e);
	}
	public static Expression many(String e) {
		return many(string(e));
	}
	public static Expression many(char e) {
		return many(character(e));
	}
	public static Expression repeat(Expression e, int times) {
		return new Repeat(e, times);
	}
	public static Expression repeat(String e, int times) {
		return repeat(string(e), times);
	}
	public static Expression repeat(char e, int times) {
		return repeat(character(e), times);
	}
	public static Expression repeat(Expression e, int timesMin, int timesMax) {
		return new Repeat(e, timesMin, timesMax);
	}
	public static Expression repeat(String e, int timesMin, int timesMax) {
		return repeat(string(e), timesMin, timesMax);
	}
	public static Expression repeat(char e, int timesMin, int timesMax) {
		return repeat(character(e), timesMin, timesMax);
	}
	public static Expression many1(Expression e) {
		return new Many1(e);
	}
	public static Expression many1(String e) {
		return many1(string(e));
	}
	public static Expression many1(char e) {
		return many1(character(e));
	}
	public static Expression sequence(Expression... es) {
		return new Sequence(es);
	}
	public static Expression sequence(Object... os) {
		return sequence(convertStrings(os));
	}
	public static Expression choice(Expression... es) {
		return new Choice(es);
	}
	public static Expression choice(Object... es) {
		return sequence(convertStrings(es));
	}
	public static Expression optional(Expression e) {
		return new Optional(e);
	}
	public static Expression optional(String e) {
		return optional(string(e));
	}
	public static Expression optional(char e) {
		return optional(character(e));
	}
	public static Expression separatedBy(Expression separator, Expression e) {
		return optional(separatedBy1(separator, e));
	}
	public static Expression separatedBy(char separator, Expression e) {
		return optional(separatedBy1(character(separator), e));
	}
	public static Expression separatedBy(String separator, Expression e) {
		return separatedBy(string(separator), e);
	}
	public static Expression separatedBy(Expression separator, String e) {
		return separatedBy(separator, string(e));
	}
	public static Expression separatedBy(Expression separator, char e) {
		return separatedBy(separator, character(e));
	}
	public static Expression separatedBy(String separator, String e) {
		return separatedBy(string(separator), string(e));
	}
	public static Expression separatedBy(char separator, String e) {
		return separatedBy(character(separator), string(e));
	}
	public static Expression separatedBy(String separator, char e) {
		return separatedBy(string(separator), character(e));
	}
	public static Expression separatedBy(char separator, char e) {
		return separatedBy(character(separator), character(e));
	}
	public static Expression separatedBy1(Expression separator, Expression e) {
		return sequence(e,many(sequence(separator, e)));
	}
	public static Expression separatedBy1(String separator, Expression e) {
		return separatedBy1(string(separator), e);
	}
	public static Expression separatedBy1(Expression separator, String e) {
		return separatedBy1(separator, string(e));
	}
	public static Expression separatedBy1(Expression separator, char e) {
		return separatedBy1(separator, character(e));
	}
	public static Expression separatedBy1(String separator, String e) {
		return separatedBy1(string(separator), string(e));
	}
	public static Expression separatedBy1(char separator, String e) {
		return separatedBy1(character(separator), string(e));
	}
	public static Expression separatedBy1(String separator, char e) {
		return separatedBy1(string(separator), character(e));
	}
	public static Expression separatedBy1(char separator, char e) {
		return separatedBy1(character(separator), character(e));
	}
	public static Expression word() {
		return many1(charClass(CharClass.wordChar()));
	}
	public static Expression number() {
		return many1(digit());
	}
	public static Expression whitespace() {
		return many(charClass(CharClass.whitespaceChar()));
	}
	public static Expression whitespace1() {
		return many1(charClass(CharClass.whitespaceChar()));
	}
	public static CaptureGroup capture(Expression expression) {
		return new CaptureGroup(expression);
	}
	public static CaptureGroup capture(String e) {
		return new CaptureGroup(string(e));
	}
	public static CaptureGroup capture(char e) {
		return new CaptureGroup(character(e));
	}
	public static Expression backReference(CaptureGroup group) {
		return new BackReference(group);
	}
	public static Replacement replacement(Object... os) {
		Replacement replacement = new Replacement();
		for(Object o : os) {
			if(o instanceof String) {
				replacement.addPart(
					new StringReplacementPart((String)o)
				);
			} else if(o instanceof Character) {
				replacement.addPart(
					new StringReplacementPart(""+(Character)o)
				);
			} else if(o instanceof CaptureGroup) {
				replacement.addPart(
					new CaptureGroupReplacementPart((CaptureGroup)o)
				);
			}
		}
		return replacement;
	}
	private static Expression[] convertStrings(Object[] os) {
		Expression[] es = new Expression[os.length];
		for(int i = 0; i < os.length; ++i) {
			if(os[i] instanceof Expression) {
				es[i] = (Expression)os[i];
			} else if (os[i] instanceof String) {
				es[i] = string((String)os[i]);
			} else if (os[i] instanceof Character) {
				es[i] = character((Character)os[i]);
			}
		}
		return es;
	}
}