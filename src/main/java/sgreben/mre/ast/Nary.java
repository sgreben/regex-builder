package sgreben.mre.ast;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collections;

public class Nary implements Ast {
	private final List<Ast> children;
	
	public Nary(final Ast... childrenArray) {
		this.children = Collections.unmodifiableList(
			Arrays.asList(childrenArray)
		);
	}
	
	public Iterator<Ast> children() {
		return children.iterator();
	}
}