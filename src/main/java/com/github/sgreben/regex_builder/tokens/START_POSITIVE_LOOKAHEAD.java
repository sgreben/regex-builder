package com.github.sgreben.regex_builder.tokens;

public class START_POSITIVE_LOOKAHEAD implements TOKEN {
	public String regexString() {
		return "(?=";
	}
}