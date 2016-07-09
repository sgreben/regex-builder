package sgreben.mre.expression;

public interface Expression {
	java.lang.Iterable<Expression> children();
	void accept(ExpressionVisitor visitor);
	void compile(StringBuilder sb);
}