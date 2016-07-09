package sgreben.mre;

import sgreben.mre.expression.*;
import sgreben.mre.compiler.Compiler;

public class Mre {
	public static Expression string(String s) {
		return new Literal(s);
	}
	public static Expression character(char c) {
		return string(""+c);
	}
	public static Expression anyCharacter() {
		return new AnyCharacter();
	}
	public static Expression charClass(String charClass) {
		return new CharClass(charClass);
	}
	public static Expression wordCharacter() {
		return new CharClass(("\\w"));
	}
	public static Expression nonWordCharacter() {
		return new CharClass(("\\W"));
	}
	public static Expression digit() {
		return new CharClass(("\\d"));
	}
	public static Expression nonDigit() {
		return new CharClass(("\\D"));
	}
	public static Expression whitespaceCharacter() {
		return new CharClass(("\\s"));
	}
	public static Expression nonWhitespaceCharacter() {
		return new CharClass(("\\S"));
	}
	public static Expression many(Expression e) {
		return new Many(e);
	}
	public static Expression many1(Expression e) {
		return new Many1(e);
	}
	public static Expression sequence(Expression... es) {
		return new Sequence(es);
	}
	public static Expression choice(Expression... es) {
		return new Choice(es);
	}
	public static Expression optional(Expression e) {
		return new Optional(e);
	}
	public static Expression separatedBy(Expression separator, Expression e) {
		return optional(separatedBy1(separator, e));
	}
	public static Expression separatedBy1(Expression separator, Expression e) {
		return sequence(e,many(sequence(separator, e)));
	}
	public static Expression word() {
		return many1(wordCharacter());
	}
	public static Expression number() {
		return many1(digit());
	}
	public static Expression whitespace() {
		return many(whitespaceCharacter());
	}
	public static Expression whitespace1() {
		return many1(whitespaceCharacter());
	}
	public static CaptureGroup capture(Expression expression) {
		return new CaptureGroup(expression);
	}
	public static Pattern compile(Expression expression) {
		return Compiler.compile(expression);
	}

}