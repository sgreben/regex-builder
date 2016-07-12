package sgreben.regex_builder;

import sgreben.regex_builder.expression.ExpressionVisitor;
import sgreben.regex_builder.tokens.TOKEN;

public interface Expression {
	java.lang.Iterable<Expression> children();
	void accept(ExpressionVisitor visitor);
	void compile(java.util.List<TOKEN> output);
}