package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class NonDigit extends Nullary {
	public NonDigit() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\D"));
	}
}