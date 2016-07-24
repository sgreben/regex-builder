package sgreben.regex_builder.tokens;

public class END_GROUP implements TOKEN {
	public String regexString(sgreben.regex_builder.CaptureGroupIndex index) {
		return ")";
	}
}
