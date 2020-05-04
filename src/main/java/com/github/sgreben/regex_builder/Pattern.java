package com.github.sgreben.regex_builder;

import com.github.sgreben.regex_builder.compiler.Compiler;

public class Pattern {
	private final java.util.regex.Pattern rawPattern;
	private final CaptureGroupIndex groupIndex;

	public static Pattern compile(Expression expression) {
		return Compiler.compile(expression);
	}

	public static Pattern compile(Expression expression, int flags) {
		return Compiler.compile(expression, flags);
	}

	public static Pattern quote(String literal) {
		return Compiler.compile(Re.string(literal));
	}

	public Pattern(java.util.regex.Pattern rawPattern, CaptureGroupIndex groupIndex) {
		this.rawPattern = rawPattern;
		this.groupIndex = groupIndex;
	}

	public Matcher matcher(CharSequence input) {
		java.util.regex.Matcher matcher = rawPattern.matcher(input);
		return new Matcher(matcher, groupIndex);
	}

	public static boolean matches(Expression regex, CharSequence input) {
		return compile(regex).matcher(input).matches();
	}

	public String[] split(CharSequence input) {
		return rawPattern.split(input);
	}

	public String[] split(CharSequence input, int limit) {
		return rawPattern.split(input, limit);
	}

	public java.util.stream.Stream<String> splitAsStream(CharSequence input) {
		return rawPattern.splitAsStream(input);
	}

	public String pattern() {
		return rawPattern.pattern();
	}

	@Override
	public String toString() {
		return rawPattern.toString();
	}
}
