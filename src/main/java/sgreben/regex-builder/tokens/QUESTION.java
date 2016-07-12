package sgreben.mre.tokens;

import java.util.regex.Pattern;

public class QUESTION implements TOKEN {
	public String regexString() {
		return "?";
	}
}