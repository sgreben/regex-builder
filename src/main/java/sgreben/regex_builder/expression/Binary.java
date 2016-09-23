package sgreben.regex_builder.expression;

import sgreben.regex_builder.Expression;
import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;
import java.util.Collections;

abstract class Binary extends ExpressionBase implements Expression {
	private final List<Expression> children;
	
	public Binary(Expression leftChild, Expression rightChild) {
		List<Expression> children = new LinkedList<Expression>();
		children.add(leftChild);
		children.add(rightChild);
		this.children = Collections.unmodifiableList(children);
	}
	
	public Iterable<Expression> children() {
		return children;
	}
}