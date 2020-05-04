package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.RAW;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class NonWordBoundary extends Nullary {
	public NonWordBoundary() {
	}

	@Override
	public CharClass complement() {
		return new WordBoundary();
	}

	@Override
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\B"));
	}
}
