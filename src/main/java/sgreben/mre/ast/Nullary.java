package sgreben.mre.ast;

import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;
import java.util.Collections;

abstract class Nullary extends AstBase implements Ast {
	private static final List<Ast> empty = 
		Collections.unmodifiableList(new LinkedList<Ast>());
	
	public Nullary() {}
	
	public Iterable<Ast> children() {
		return empty;
	}
}