package sgreben.mre;

import sgreben.mre.ast.Ast;
import sgreben.mre.charclass.CharClass;

public class Mre {
	public static Ast string(String s) {
		return new sgreben.mre.ast.Literal(s);
	}
	public static Ast character(char c) {
		return string(""+c);
	}
	public static Ast anyCharacter() {
		return new sgreben.mre.ast.AnyCharacter();
	}
	public static Ast charClass(CharClass charClass) {
		return new sgreben.mre.ast.CharClass(charClass);
	}
	public static Ast wordCharacter() {
		return new sgreben.mre.ast.CharClass(new CharClass("\\W"));
	}
	public static Ast nonWordCharacter() {
		return new sgreben.mre.ast.CharClass(new CharClass("\\W"));
	}
	public static Ast digit() {
		return new sgreben.mre.ast.CharClass(new CharClass("\\d"));
	}
	public static Ast nonDigit() {
		return new sgreben.mre.ast.CharClass(new CharClass("\\D"));
	}
	public static Ast whitespaceCharacter() {
		return new sgreben.mre.ast.CharClass(new CharClass("\\s"));
	}
	public static Ast nonWhitespaceCharacter() {
		return new sgreben.mre.ast.CharClass(new CharClass("\\S"));
	}
	public static Ast many(Ast e) {
		return new sgreben.mre.ast.Many(e);
	}
	public static Ast many1(Ast e) {
		return new sgreben.mre.ast.Many1(e);
	}
	public static Ast sequence(Ast... es) {
		return new sgreben.mre.ast.Sequence(es);
	}
	public static Ast choice(Ast... es) {
		return new sgreben.mre.ast.Choice(es);
	}
	public static Ast optional(Ast e) {
		return new sgreben.mre.ast.Optional(e);
	}
	public static Ast separatedBy(Ast separator, Ast e) {
		return optional(separatedBy1(separator, e));
	}
	public static Ast separatedBy1(Ast separator, Ast e) {
		return sequence(e,many(sequence(separator, e)));
	}
	public static Ast word() {
		return many1(wordCharacter());
	}
	public static Ast number() {
		return many1(digit());
	}
	public static Ast whitespace() {
		return many(whitespaceCharacter());
	}
	public static Ast whitespace1() {
		return many1(whitespaceCharacter());
	}
	public static CaptureGroup capture(Ast ast) {
		return new CaptureGroup(ast);
	}
	public static Matcher compile(Ast ast) {
		return null;
	}

}