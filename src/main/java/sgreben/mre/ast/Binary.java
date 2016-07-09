package sgreben.mre.ast;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;

public class Binary implements Ast {
	private final Ast leftChild;
	private final Ast rightChild;
	private final List<Ast> children;
	
	public Binary(Ast leftChild, Ast rightChild) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		List<Ast> children = new LinkedList<Ast>();
		children.add(leftChild);
		children.add(rightChild);
		this.children = Collections.unmodifiableList(children);
	}
	
	public Iterator<Ast> children() {
		return children.iterator();
	}
}