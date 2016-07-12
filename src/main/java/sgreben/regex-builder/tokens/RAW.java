package sgreben.regex_builder.tokens;

import java.util.regex.Pattern;

public class RAW implements TOKEN {
	private final String charClassRegex;
	
	public RAW(String charClassRegex) {
		this.charClassRegex = charClassRegex; 
	}
	
	public String regexString() {
		return charClassRegex;
	}
}