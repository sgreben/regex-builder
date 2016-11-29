package com.github.sgreben.regex_builder.tokens;

import java.util.regex.Pattern;

public class LITERAL implements TOKEN {
	private final String literalRegex;
	
	public LITERAL(String literal) {
		this.literalRegex = Pattern.quote(literal); 
	}
	
	public String regexString() {
		return literalRegex;
	}
}