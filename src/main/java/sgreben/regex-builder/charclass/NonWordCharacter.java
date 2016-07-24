package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class NonWordCharacter extends Nullary {
	public NonWordCharacter() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\W"));
	}
}