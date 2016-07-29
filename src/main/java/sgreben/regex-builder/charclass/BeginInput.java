package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class BeginInput extends Nullary {
	public BeginInput() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\A"));
	}
}