package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class OneOf extends Nullary {
	private final String chars;
	public OneOf(String chars) {
		this.chars = chars;
	}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_CHAR_CLASS());
		output.add(new RAW(chars));
		output.add(new END_CHAR_CLASS());
	}
}