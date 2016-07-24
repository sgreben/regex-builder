package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class Whitespace extends Nullary {
	public Whitespace() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\s"));
	}
}