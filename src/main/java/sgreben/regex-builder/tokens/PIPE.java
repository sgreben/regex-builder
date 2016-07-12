package sgreben.mre.tokens;

import java.util.regex.Pattern;

public class PIPE implements TOKEN {
	public String regexString() {
		return "|";
	}
}