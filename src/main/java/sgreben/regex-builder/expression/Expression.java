package sgreben.regex_builder.expression;

public interface Expression {
	java.lang.Iterable<Expression> children();
	void accept(ExpressionVisitor visitor);
	void compile(java.util.List<sgreben.regex_builder.tokens.TOKEN> output);
}