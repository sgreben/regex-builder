package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.tokens.RAW;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class EndInput extends Nullary {
	public EndInput() {}
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\z"));
	}
}