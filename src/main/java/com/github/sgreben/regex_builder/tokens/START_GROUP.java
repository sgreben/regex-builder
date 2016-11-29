package com.github.sgreben.regex_builder.tokens;

public class START_GROUP implements TOKEN {
	public String regexString() {
		return "(";
	}
}