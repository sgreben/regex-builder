package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class VerticalWhitespace extends Nullary {
	public VerticalWhitespace() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\v"));
	}
}