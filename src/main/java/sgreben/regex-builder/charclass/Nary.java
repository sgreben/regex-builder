package sgreben.regex_builder.charclass;

import sgreben.regex_builder.CharClass;
import java.util.List;
import java.util.Arrays;
import java.lang.Iterable;
import java.util.Collections;

abstract class Nary extends CharClassBase implements CharClass {
	private final List<CharClass> children;
	
	public Nary(final CharClass... childrenArray) {
		this.children = Collections.unmodifiableList(
			Arrays.asList(childrenArray)
		);
	}
	
	public Iterable<CharClass> children() {
		return children;
	}
}