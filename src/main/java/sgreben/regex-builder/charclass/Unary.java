package sgreben.regex_builder.charclass;

import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;
import java.util.Collections;
import sgreben.regex_builder.CharClass;

public abstract class Unary extends CharClassBase {
	private final CharClass child;
	private final List<CharClass> children;
	
	public Unary(CharClass child) {
		this.child = child;
		List<CharClass> children = new LinkedList<CharClass>();
		children.add(child);
		this.children = Collections.unmodifiableList(children);
	}
	
	public Iterable<CharClass> children() {
		return children;
	}
}