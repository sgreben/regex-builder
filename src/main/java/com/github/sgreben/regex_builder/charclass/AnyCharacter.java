package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.tokens.DOT;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class AnyCharacter extends Nullary {
	public void compile(java.util.List<TOKEN> output) {
		output.add(new DOT());
	}
}