package sgreben.regex_builder.expression;

import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;
import java.util.Collections;
import sgreben.regex_builder.Expression;

public abstract class Unary extends ExpressionBase implements Expression {
	private final Expression child;
	private final List<Expression> children;
	
	public Unary(Expression child) {
		this.child = child;
		List<Expression> children = new LinkedList<Expression>();
		children.add(child);
		this.children = Collections.unmodifiableList(children);
	}
	
	public Iterable<Expression> children() {
		return children;
	}
}