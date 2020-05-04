package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.*;

public class RangeComplement extends Nullary {
	private final char[] range;
	public RangeComplement(char... range) {
		this.range = range;
	}

	@Override
	public CharClass complement() { return new RawComplement(this); }

	@Override
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_CHAR_CLASS());
		output.add(new CARET());
		for(int i = 0; i < range.length; i += 2) {
			output.add(new RAW(""+range[i]));
			output.add(new DASH());
			output.add(new RAW(""+range[i+1]));

		}
		output.add(new END_CHAR_CLASS());
	}
}
