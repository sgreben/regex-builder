package sgreben.regex_builder.expression;

import sgreben.regex_builder.Expression;

public abstract class ExpressionBase implements Expression {
	public void accept(ExpressionVisitor visitor) {
		visitor.visitPre(this);
		for(Expression child : children ()) {
			child.accept(visitor);
		}
		visitor.visitPost(this);
	}
	public Expression possessive() { return this; }
	public Expression reluctant() { return this; }
}