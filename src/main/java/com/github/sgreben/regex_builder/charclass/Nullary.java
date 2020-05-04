package com.github.sgreben.regex_builder.charclass;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.github.sgreben.regex_builder.CharClass;

abstract class Nullary extends CharClassBase {
	private static final List<CharClass> empty =
			Collections.unmodifiableList(new LinkedList<CharClass>());

	public Nullary() {
	}

	@Override
	public Iterable<CharClass> children() {
		return empty;
	}
}
