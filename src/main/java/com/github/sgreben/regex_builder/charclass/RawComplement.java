package com.github.sgreben.regex_builder.charclass;

import com.github.sgreben.regex_builder.tokens.CARET;
import com.github.sgreben.regex_builder.tokens.END_CHAR_CLASS;
import com.github.sgreben.regex_builder.tokens.START_CHAR_CLASS;
import com.github.sgreben.regex_builder.tokens.TOKEN;
import com.github.sgreben.regex_builder.CharClass;

class RawComplement extends Unary {
	final CharClass child;
	public RawComplement(final CharClass child) {
		super(child);
		this.child = child;
	}
	public CharClass complement() { return child; }
	public void compile(final java.util.List<TOKEN> output) {
		output.add(new START_CHAR_CLASS());
		output.add(new CARET());
		for(final CharClass child : children()) {
			child.compile(output);
		}
		output.add(new END_CHAR_CLASS());
	}
}
