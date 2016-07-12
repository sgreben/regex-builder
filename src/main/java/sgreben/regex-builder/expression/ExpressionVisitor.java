package sgreben.regex_builder.expression;

public interface ExpressionVisitor {
	void visitPre(Expression node);
	void visitPost(Expression node);
}