package com.github.sgreben.regex_builder.tokens;

public class START_POSITIVE_LOOKBEHIND implements TOKEN {
	public String regexString() {
		return "(?<=";
	}
}