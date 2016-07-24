package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class WordBoundary extends Nullary {
	public WordBoundary() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\b"));
	}
}