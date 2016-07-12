package sgreben.regex_builder.tokens;

public class START_GROUP_NON_CAPTURING implements TOKEN {
	public String regexString() {
		return "(?:";
	}
}