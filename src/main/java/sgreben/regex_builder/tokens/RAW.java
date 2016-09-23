package sgreben.regex_builder.tokens;

public class RAW implements TOKEN {
	private final String rawRegex;
	
	public RAW(String rawRegex) {
		this.rawRegex = rawRegex; 
	}
	
	public String regexString() {
		return rawRegex;
	}
}