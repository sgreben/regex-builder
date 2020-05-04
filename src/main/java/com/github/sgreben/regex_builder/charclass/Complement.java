package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class Complement extends Unary {
	final CharClass child;
	public Complement(final CharClass child) {
		super(child);
		this.child = child;
	}
	public CharClass complement() { return child; }
	public void compile(final java.util.List<TOKEN> output) {
		child.complement().compile(output);
	}
}
