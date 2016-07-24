package sgreben.regex_builder.tokens;

import java.util.regex.Pattern;

public class QUESTION implements TOKEN {
	public String regexString(sgreben.regex_builder.CaptureGroupIndex index) {
		return "?";
	}
}