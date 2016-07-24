package sgreben.regex_builder.tokens;

public class STAR implements TOKEN {
	public String regexString(sgreben.regex_builder.CaptureGroupIndex index) {
		return "*";
	}
}