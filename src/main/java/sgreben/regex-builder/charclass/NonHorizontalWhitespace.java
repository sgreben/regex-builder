package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class NonHorizontalWhitespace extends Nullary {
	public NonHorizontalWhitespace() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\H"));
	}
}