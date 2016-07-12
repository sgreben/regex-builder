package sgreben.regex_builder.expression;

import java.util.List;
import java.util.Arrays;
import java.lang.Iterable;
import java.util.Collections;

abstract class Nary extends ExpressionBase implements Expression {
	private final List<Expression> children;
	
	public Nary(final Expression... childrenArray) {
		this.children = Collections.unmodifiableList(
			Arrays.asList(childrenArray)
		);
	}
	
	public Iterable<Expression> children() {
		return children;
	}
}