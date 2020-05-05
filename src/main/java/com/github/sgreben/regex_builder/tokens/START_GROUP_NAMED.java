package com.github.sgreben.regex_builder.tokens;

public class START_GROUP_NAMED implements TOKEN {
	private final String name;

	public START_GROUP_NAMED(String name) {
		this.name = name;
	}

	public String regexString() {
		return "(?<" + name + ">";
	}
}
