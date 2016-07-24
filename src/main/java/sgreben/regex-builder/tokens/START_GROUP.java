package sgreben.regex_builder.tokens;

public class START_GROUP implements TOKEN {
	public String regexString(sgreben.regex_builder.CaptureGroupIndex index) {
		return "(";
	}
}