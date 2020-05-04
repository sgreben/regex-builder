package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.RAW;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class Digit extends Nullary {
	public Digit() {}
	public CharClass complement() { return new NonDigit(); }
	public void compile(java.util.List<TOKEN> output) {
		output.add(new RAW("\\d"));
	}
}
