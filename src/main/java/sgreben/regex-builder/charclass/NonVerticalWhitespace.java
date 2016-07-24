package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class NonVerticalWhitespace extends Nullary {
	public NonVerticalWhitespace() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\V"));
	}
}