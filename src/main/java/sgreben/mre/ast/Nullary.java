package sgreben.mre.ast;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class Nullary implements Ast {
	private static final List<Ast> empty = 
		Collections.unmodifiableList(new LinkedList<Ast>());
	
	public Nullary() {}
	
	public Iterator<Ast> children() {
		return empty.iterator();
	}
}