package sgreben.mre.ast;

import java.lang.Iterable;

public abstract class AstBase implements Ast {
	public void accept(AstVisitor visitor) {
		visitor.visitPre(this);
		for(Ast child : children ()) {
			child.accept(visitor);
		}
		visitor.visitPost(this);
	}
}