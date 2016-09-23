package sgreben.regex_builder.tokens;

public class START_NEGATIVE_LOOKAHEAD implements TOKEN {
	public String regexString() {
		return "(?!";
	}
}