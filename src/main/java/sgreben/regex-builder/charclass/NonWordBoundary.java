package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class NonWordBoundary extends Nullary {
	public NonWordBoundary() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\B"));
	}
}