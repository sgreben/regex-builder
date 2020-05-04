package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.DOT;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class AnyCharacter extends Nullary {
	@Override
	public CharClass complement() {
		return oneOf("");
	}

	@Override
	public void compile(java.util.List<TOKEN> output) {
		output.add(new DOT());
	}
}
