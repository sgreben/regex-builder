package sgreben.regex_builder;

import sgreben.regex_builder.compiler.Compiler;

public class Pattern {
	private final java.util.regex.Pattern rawPattern;
	private final CaptureGroupIndex groupIndex;
	
	public Pattern(java.util.regex.Pattern rawPattern, 
				   CaptureGroupIndex groupIndex) {
		this.rawPattern = rawPattern;
		this.groupIndex = groupIndex;
	}
	
	public Matcher matcher(String text) {
		java.util.regex.Matcher matcher = rawPattern.matcher(text);
		return new Matcher(matcher, groupIndex);
	}
	
	public static Pattern compile(Expression expression) {
		return Compiler.compile(expression);
	}
	public static Pattern quote(String literal) {
		return Compiler.compile(Re.string(literal));
	}
	public static Pattern compile(char literal) {
		return Compiler.compile(Re.character(literal));
	}
}