package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.tokens.RAW;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class EndInputBeforeFinalTerminator extends Nullary {
	public EndInputBeforeFinalTerminator() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\z"));
	}
}