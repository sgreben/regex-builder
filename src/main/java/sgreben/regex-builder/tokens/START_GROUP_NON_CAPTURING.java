package sgreben.regex_builder.tokens;

public class START_GROUP_NON_CAPTURING implements TOKEN {
	public String regexString(sgreben.regex_builder.CaptureGroupIndex index) {
		return "(?:";
	}
}