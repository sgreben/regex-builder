package com.github.sgreben.regex_builder.charclass;

import java.util.ArrayList;
import java.util.List;

import com.github.sgreben.regex_builder.CharClass;
import com.github.sgreben.regex_builder.tokens.END_CHAR_CLASS;
import com.github.sgreben.regex_builder.tokens.START_CHAR_CLASS;
import com.github.sgreben.regex_builder.tokens.TOKEN;

public class Union extends Nary {
	public Union(CharClass... children) { super(children); }

	public CharClass complement() {
		final List<CharClass> children = new ArrayList<>();
		for (CharClass child : this.children()) {
			children.add(child.complement());
		}
		return new Intersection(children.toArray(new CharClass[children.size()]));
	}

	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_CHAR_CLASS());
		for(CharClass child : children()) {
			child.compile(output);
		}
		output.add(new END_CHAR_CLASS());
	}
}
