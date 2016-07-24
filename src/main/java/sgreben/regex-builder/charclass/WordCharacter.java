package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class WordCharacter extends Nullary {
	public WordCharacter() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\w"));
	}
}