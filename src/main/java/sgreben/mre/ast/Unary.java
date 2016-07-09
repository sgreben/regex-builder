package sgreben.mre.ast;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class Unary implements Ast {
	private final Ast child;
	private final List<Ast> children;
	
	public Unary(Ast child) {
		this.child = child;
		List<Ast> children = new LinkedList<Ast>();
		children.add(child);
		this.children = Collections.unmodifiableList(children);
	}
	
	public Iterator<Ast> children() {
		return children.iterator();
	}
}