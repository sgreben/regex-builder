package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.*;

public class Range extends Nullary {
	private final char[] range;
	public Range(char... range) {
		this.range = range;
	}

	@Override
	public CharClass complement() { return new RangeComplement(range); }

	@Override
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_CHAR_CLASS());
		for(int i = 0; i < range.length; i += 2) {
			output.add(new RAW(""+range[i]));
			output.add(new DASH());
			output.add(new RAW(""+range[i+1]));

		}
		output.add(new END_CHAR_CLASS());
	}
}
