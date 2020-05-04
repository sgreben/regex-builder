package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.RAW;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class NonWhitespace extends Nullary {
	public NonWhitespace() {
	}

	@Override
	public CharClass complement() {
		return new Whitespace();
	}

	@Override
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\S"));
	}
}
