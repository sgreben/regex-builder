package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class NonWhitespace extends Nullary {
	public NonWhitespace() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\S"));
	}
}