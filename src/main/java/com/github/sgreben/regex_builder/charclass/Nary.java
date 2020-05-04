package com.github.sgreben.regex_builder.charclass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.github.sgreben.regex_builder.CharClass;

abstract class Nary extends CharClassBase {
	private final List<CharClass> children;

	public Nary(final CharClass... childrenArray) {
		this.children = Collections.unmodifiableList(Arrays.asList(childrenArray));
	}

	@Override
	public Iterable<CharClass> children() {
		return children;
	}
}
