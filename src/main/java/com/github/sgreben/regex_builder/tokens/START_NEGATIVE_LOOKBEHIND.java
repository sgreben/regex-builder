package com.github.sgreben.regex_builder.tokens;

public class START_NEGATIVE_LOOKBEHIND implements TOKEN {
	public String regexString() {
		return "(?<!";
	}
}