package com.github.sgreben.regex_builder.charclass;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.github.sgreben.regex_builder.CharClass;

public abstract class Unary extends CharClassBase {
	private final List<CharClass> children;

	public Unary(CharClass child) {
		List<CharClass> children = new LinkedList<CharClass>();
		children.add(child);
		this.children = Collections.unmodifiableList(children);
	}

	@Override
	public Iterable<CharClass> children() {
		return children;
	}
}
