package sgreben.regex_builder.tokens;

import java.util.regex.Pattern;

public class QUESTION implements TOKEN {
	public String regexString() {
		return "?";
	}
}