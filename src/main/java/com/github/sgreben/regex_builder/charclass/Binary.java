package com.github.sgreben.regex_builder.charclass;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.github.sgreben.regex_builder.CharClass;

abstract class Binary extends CharClassBase {
	private final List<CharClass> children;

	public Binary(CharClass leftChild, CharClass rightChild) {
		List<CharClass> children = new LinkedList<CharClass>();
		children.add(leftChild);
		children.add(rightChild);
		this.children = Collections.unmodifiableList(children);
	}

	@Override
	public Iterable<CharClass> children() {
		return children;
	}
}
