package sgreben.regex_builder.expression;

import java.lang.Iterable;

public abstract class ExpressionBase implements Expression {
	public void accept(ExpressionVisitor visitor) {
		visitor.visitPre(this);
		for(Expression child : children ()) {
			child.accept(visitor);
		}
		visitor.visitPost(this);
	}
}