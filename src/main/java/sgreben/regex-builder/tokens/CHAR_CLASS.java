package sgreben.regex_builder.tokens;

import java.util.regex.Pattern;

public class CHAR_CLASS implements TOKEN {
	private final String charClassRegex;
	
	public CHAR_CLASS(String charClassRegex) {
		this.charClassRegex = charClassRegex; 
	}
	
	public String regexString() {
		return charClassRegex;
	}
}