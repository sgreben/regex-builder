package sgreben.mre.ast;

import java.util.List;
import java.util.Arrays;
import java.lang.Iterable;
import java.util.Collections;

abstract class Nary extends AstBase implements Ast {
	private final List<Ast> children;
	
	public Nary(final Ast... childrenArray) {
		this.children = Collections.unmodifiableList(
			Arrays.asList(childrenArray)
		);
	}
	
	public Iterable<Ast> children() {
		return children;
	}
}