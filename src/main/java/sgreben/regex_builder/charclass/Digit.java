package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class Digit extends Nullary {
	public Digit() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\d"));
	}
}