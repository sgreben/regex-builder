package sgreben.regex_builder.charclass;

import sgreben.regex_builder.tokens.*;

public class Range extends Nullary {
	private final char from;
	private final char to;
	public Range(char from, char to) {
		assert(from <= to);
		this.from = from;
		this.to = to;
	}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_CHAR_CLASS());
		output.add(new RAW(""+from));
		output.add(new DASH());
		output.add(new RAW(""+to));
		output.add(new END_CHAR_CLASS());
	}
}