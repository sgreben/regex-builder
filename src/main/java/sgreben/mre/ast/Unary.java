package sgreben.mre.ast;

import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;
import java.util.Collections;

public abstract class Unary extends AstBase implements Ast {
	private final Ast child;
	private final List<Ast> children;
	
	public Unary(Ast child) {
		this.child = child;
		List<Ast> children = new LinkedList<Ast>();
		children.add(child);
		this.children = Collections.unmodifiableList(children);
	}
	
	public Iterable<Ast> children() {
		return children;
	}
}