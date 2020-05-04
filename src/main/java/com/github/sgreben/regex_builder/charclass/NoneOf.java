package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.CARET;
import com.github.sgreben.regex_builder.tokens.END_CHAR_CLASS;
import com.github.sgreben.regex_builder.tokens.RAW;
import com.github.sgreben.regex_builder.tokens.START_CHAR_CLASS;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class NoneOf extends Nullary {
	private final String chars;

	public NoneOf(String chars) {
		this.chars = chars;
	}

	@Override
	public CharClass complement() {
		return new OneOf(chars);
	}

	@Override
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_CHAR_CLASS());
		output.add(new CARET());
		output.add(new RAW(chars));
		output.add(new END_CHAR_CLASS());
	}
}
