package sgreben.regex_builder.expression;

import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;
import java.util.Collections;
import sgreben.regex_builder.Expression;

abstract class Nullary extends ExpressionBase implements Expression {
	private static final List<Expression> empty = 
		Collections.unmodifiableList(new LinkedList<Expression>());
	
	public Nullary() {}
	
	public Iterable<Expression> children() {
		return empty;
	}
}