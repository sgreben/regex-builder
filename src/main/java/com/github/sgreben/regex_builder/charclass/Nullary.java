package com.github.sgreben.regex_builder.charclass;

import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;
import java.util.Collections;

import com.github.sgreben.regex_builder.CharClass;

abstract class Nullary extends CharClassBase {
	private static final List<CharClass> empty =
		Collections.unmodifiableList(new LinkedList<CharClass>());
	
	public Nullary() {}
	
	public Iterable<CharClass> children() {
		return empty;
	}
}